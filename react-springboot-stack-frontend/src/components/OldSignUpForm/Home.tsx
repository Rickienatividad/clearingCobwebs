import axios from "axios";
import "./Home.css"
import { FormEvent, useEffect, useRef, useState } from "react";
import { NavLink } from "react-router";

export function Home() {
  //regex for sign up form validations
  const emailRegex: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const passwordRegex: RegExp = /^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,12}$/;
  const securityAnswerRegex: RegExp = /^[0-9a-zA-Z ]{2,}$/;
  const nameRegex: RegExp = /^[a-zA-Z ]{2,}$/

  //for observing sign up form field input values (more validation stuff)
  const emailRef = useRef<HTMLInputElement | null>(null);
  const firstNameRef = useRef<HTMLInputElement | null>(null);
  const lastNameRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const passwordConfirmationRef = useRef<HTMLInputElement | null>(null);
  const securityAnswerRef = useRef<HTMLInputElement | null>(null);

  //stateful variables (even more validation stuff)
  const [isEmailValid, setIsEmailValid] = useState(false);
  const [isFirstNameValid, setIsFirstNameValid] = useState(false);
  const [isLastNameValid, setIsLastNameValid] = useState(false);
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] = useState(false);
  const [isSecurityAnswerValid, setIsSecurityAnswerValid] = useState(false);
  const [isFormValid, setIsFormValid] = useState(false);

  const [securityQuestions, setSecurityQuestions] = useState([]);

  const retrieveSecurityQuestions = () => {
    axios
    .get("http://localhost:8080/users/security-questions")
    .then((response) => {
      setSecurityQuestions(response.data);
    })
    .catch((error) => {
      console.log(error.message)
    });
  };

  useEffect(retrieveSecurityQuestions, []);

  const validationChecker = () => {
      if(nameRegex.test(firstNameRef.current?.value ?? "")) {
        setIsFirstNameValid(true)} else {
          setIsFirstNameValid(false);
      };
      
      if(nameRegex.test(lastNameRef.current?.value ?? "")) {
        setIsLastNameValid(true)} else {
          setIsLastNameValid(false);
      };

      if(emailRegex.test(emailRef.current?.value ?? "")) {
      setIsEmailValid(true)} else {
        setIsEmailValid(false);
      };
      if(passwordRegex.test(passwordRef.current?.value ?? "")) {
      setIsPasswordValid(true)} else {
        setIsPasswordValid(false);
      };
      if (passwordConfirmationRef.current?.value !== passwordRef.current?.value){
      setIsPasswordConfirmationValid(false);
      } else {
        setIsPasswordConfirmationValid(true);
      };
      if((!securityAnswerRegex.test(securityAnswerRef.current?.value ?? "")) || (securityAnswerRef.current?.value.length != null && securityAnswerRef.current.value.length < 3)) {
        setIsSecurityAnswerValid(false);
      } else {
          setIsSecurityAnswerValid(true);
        }
  };

  useEffect(() => {
    if(!isFirstNameValid || !isLastNameValid || !isEmailValid || !isPasswordValid || !isPasswordConfirmationValid || !isSecurityAnswerValid) {
      setIsFormValid(false);
    } else {
      setIsFormValid(true);
    }
  }, [isFirstNameValid, isLastNameValid, isEmailValid, isPasswordValid, isPasswordConfirmationValid, isSecurityAnswerValid , isFormValid])
  
  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const params: FormData = new FormData(e.currentTarget);
    const userData = Object.fromEntries(params)
    const userObject: object = {
      firstName: userData.firstName,
      lastName: userData.lastName,
      email: userData.email,
      password: userData.password,
      passwordConfirmation: userData.passwordConfirmation,
      securityQuestion: userData.securityQuestion,
      securityAnswer: userData.securityAnswer
    }
    axios
      .post("http://localhost:8080/users", userObject)
      .then((response) => {
        console.log(response.data);
        window.location.href = "/";
      })
      .catch((error) => {
        console.log(error.message);
      });
      
  }

  return (
    <>
    <h3>Sign Up Today!</h3>
    <div id="main-container" className="form-group">
      <form onSubmit={handleSubmit}>
        
        <input ref={firstNameRef} name="firstName" type="text" className="form-control-lg" placeholder="First Name" onChange={validationChecker} />
        <div className={!isFirstNameValid ? "help-text" : "help-text-hidden"}>At least two letters are required. No special characters. </div>
        <input ref={lastNameRef} name="lastName" type="text" className="form-control-lg" placeholder="Last Name" onChange={validationChecker}/>
        <div className={!isLastNameValid ? "help-text" : "help-text-hidden"}>At least two letters are required. No specials characters.</div>
        <input ref={emailRef} name="email" type="text" className="form-control-lg" placeholder="Email Address" onChange={validationChecker}/>
        <div id="email-help-block" className={!isEmailValid ? "help-text" : "help-text-hidden"}>
          Please enter a valid email address.
        </div>
        <input ref={passwordRef} name="password" type="password" className="form-control-lg" placeholder="Password" onChange={validationChecker}/>
        <div id="password-help-block" className={!isPasswordValid ? "help-text" : "help-text-hidden"}>
          Must be 8-12 characters long and contain letters, numbers and a special character.
        </div>
        <input ref={passwordConfirmationRef} name="passwordConfirmation" type="password" className="form-control-lg" placeholder="Confirm Password" onChange={validationChecker}/>
        <div id="password-confirmation-help-block" className={!isPasswordConfirmationValid ? "help-text" : "help-text-hidden"}>
            Passwords do not match.
        </div>
        <div className="btn-group">
          <select name="securityQuestion" id="security-question">
            {securityQuestions.map((question, index) =>(
              <option key={index}>{question}</option>
            ))}
          </select>
        </div>
        <input ref={securityAnswerRef} name="securityAnswer" type="text" className="form-control-lg" placeholder="Provide Answer to Selected Security Question" onChange={validationChecker}/>
        <div className={!isSecurityAnswerValid ? "help-text" : "help-text-hidden"}>Only letters and numbers allowed</div>
      <button className={isFormValid ? "continue-btn" : "continue-btn-disabled"} type="submit" disabled={!isFormValid}>Continue</button>
      </form>
      <div style={{marginRight: '-5.5rem',marginTop: '1.5rem', textAlign: 'right'}}>
        <p style={{display: 'inline'}}>Already a user? </p>
        <NavLink to="/login">Login</NavLink>
      </div>
    </div>
    </>
  )
}
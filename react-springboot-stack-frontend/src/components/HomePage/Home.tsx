import "./Home.css"
import { FormEvent, useRef, useState } from "react";

export function Home() {
  const emailRegex: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const passwordRegex: RegExp = /^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,12}$/;
  const securityAnswerRegex: RegExp = /^[0-9a-zA-Z ]{2,}$/;
  const emailRef = useRef<HTMLInputElement | null>(null);
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const passwordConfirmationRef = useRef<HTMLInputElement | null>(null);
  const securityAnswerRef = useRef<HTMLInputElement | null>(null);

  const [isPasswordValid, setIsPasswordValid] = useState(true);
  const [isEmailValid, setIsEmailValid] = useState(true);
  const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] = useState(true);
  const [isSecurityAnswerValid, setIsSecurityAnswerValid] = useState(true);

  const validationChecker = () => {
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
    console.log(securityAnswerRef.current?.value.length)
  }
  
  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    //const data: FormData = new FormData(e.currentTarget);
    validationChecker();   
  }

  return (
    <>
    <h3>Sign Up Today!</h3>
    <div id="main-container" className="form-group">
      <form onSubmit={handleSubmit}>
        
        <input ref={emailRef} name="email" type="text" className="form-control-lg" placeholder="Email Address" />
        <div id="email-help-block" className={!isEmailValid ? "help-text" : "help-text-hidden"}>
          Please enter a valid email address.
        </div>
        <input ref={passwordRef} name="password" type="password" className="form-control-lg" placeholder="Password"/>
        <div id="password-help-block" className={!isPasswordValid ? "help-text" : "help-text-hidden"}>
          Must be 8-12 characters long and contain letters, numbers and a special character.
        </div>
        <input ref={passwordConfirmationRef} name="password-confirmation" type="password" className="form-control-lg" placeholder="Confirm Password"/>
        <div id="password-confirmation-help-block" className={!isPasswordConfirmationValid ? "help-text" : "help-text-hidden"}>
            Passwords do not match.
        </div>
        <div className="btn-group">
          <select name="security-question" id="security-question">
            <option value="">Choice 1</option>
          </select>
        </div>
        <input ref={securityAnswerRef} name="security-answer" type="text" className="form-control-lg" placeholder="Provide Answer to Selected Security Question"/>
        <div className={!isSecurityAnswerValid ? "help-text" : "help-text-hidden"}>Only letters and numbers allowed</div>
      <button className="continue-btn" type="submit">Continue</button>
      </form>
    </div>
    </>
  )
}
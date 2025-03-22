import { useRef, useState } from "react";
import "../OldSignUpForm/Home.css"
import classNames from "classnames";

export function StepOne() {

  //stateful variables (even more validation stuff)
  const [isEmailValid, setIsEmailValid] = useState(false);
  const [isFirstNameValid, setIsFirstNameValid] = useState(false);
  const [isLastNameValid, setIsLastNameValid] = useState(false);

  //regex for sign up form validations
  const emailRegex: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const nameRegex: RegExp = /^[a-zA-Z ]{2,}$/

  //for observing sign up form field input values (more validation stuff)
  const emailRef = useRef<HTMLInputElement | null>(null);
  const firstNameRef = useRef<HTMLInputElement | null>(null);
  const lastNameRef = useRef<HTMLInputElement | null>(null);

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

  }

  return(
    <>
    <input ref={firstNameRef} name="firstName" type="text" className={"form-control-lg"} placeholder="First Name" onChange={validationChecker} />
    <div className={!isFirstNameValid ? "help-text" : "help-text-hidden"}>At least two letters are required. No special characters. </div>
    <div className="spacer"></div>
    <input ref={lastNameRef} name="lastName" type="text" className="form-control-lg" placeholder="Last Name" onChange={validationChecker}/>
    <div className={!isLastNameValid ? "help-text" : "help-text-hidden"}>At least two letters are required. No specials characters.</div>
    <div className="spacer"></div>
    <input ref={emailRef} name="email" type="text" className="form-control-lg" placeholder="Email Address" onChange={validationChecker}/>
    <div id="email-help-block" className={!isEmailValid ? "help-text" : "help-text-hidden"}>
      Please enter a valid email address.
    </div>
    </>
  )
}
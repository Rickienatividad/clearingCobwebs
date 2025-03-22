import { useRef, useState } from "react";

export function StepTwo(){
  const [isPasswordValid, setIsPasswordValid] = useState(false);
  const [isPasswordConfirmationValid, setIsPasswordConfirmationValid] = useState(false);

  const passwordRegex: RegExp = /^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{8,12}$/;
  const passwordRef = useRef<HTMLInputElement | null>(null);
  const passwordConfirmationRef = useRef<HTMLInputElement | null>(null);

  const validationChecker = () => {
    if(passwordRegex.test(passwordRef.current?.value ?? "")) {
      setIsPasswordValid(true)} else {
        setIsPasswordValid(false);
    };
    if (passwordConfirmationRef.current?.value !== passwordRef.current?.value) {
      setIsPasswordConfirmationValid(false);
    } else {
        setIsPasswordConfirmationValid(true);
    };
};
  
  return(
    <div className="step-two-page">
    <input ref={passwordRef} name="password" type="password" className="form-control-lg" placeholder="Password" onChange={validationChecker}/>
    <div id="password-help-block" className={!isPasswordValid ? "help-text" : "help-text-hidden"}>
      Must be 8-12 characters long and contain letters, numbers and a special character.
    </div>
    <input ref={passwordConfirmationRef} name="passwordConfirmation" type="password" className="form-control-lg" placeholder="Confirm Password" onChange={validationChecker}/>
    <div id="password-confirmation-help-block" className={!isPasswordConfirmationValid ? "help-text" : "help-text-hidden"}>
        Passwords do not match.
    </div>
    </div>
  )
}
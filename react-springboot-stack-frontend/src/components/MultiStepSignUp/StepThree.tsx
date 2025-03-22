import {useState, useRef} from "react";
import "../OldSignUpForm/Home.css";

export function StepThree(props) {

const securityAnswerRegex: RegExp = /^[0-9a-zA-Z ]{2,}$/;
const securityAnswerRef = useRef<HTMLInputElement | null>(null);
const [isSecurityAnswerValid, setIsSecurityAnswerValid] = useState(false);
const validationChecker = () => {
      if((!securityAnswerRegex.test(securityAnswerRef.current?.value ?? "")) || (securityAnswerRef.current?.value.length != null && securityAnswerRef.current.value.length < 3)) {
        setIsSecurityAnswerValid(false);
      } else {
          setIsSecurityAnswerValid(true);
        }
  };

  return(
    <div className="step-three-page">
      <div className="btn-group">
          <select name="securityQuestion" id="security-question">
            {props.secQuestions.map((question, index) =>(
              <option key={index}>{question}</option>
            ))}
          </select>
        </div>
      <div>
      <input ref={securityAnswerRef} name="securityAnswer" type="text" className="form-control-lg" placeholder="Provide Answer to Selected Security Question" onChange={validationChecker}/>
      </div>
      <div className={!isSecurityAnswerValid ? "help-text" : "help-text-hidden"}>Only letters and numbers allowed</div>
    </div>
  )
}
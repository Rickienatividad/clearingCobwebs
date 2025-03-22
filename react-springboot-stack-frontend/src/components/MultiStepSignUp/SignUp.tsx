import "./SignUp.css";
import { StepOne } from "./StepOne";
import { StepTwo } from "./StepTwo";
import { StepThree } from "./StepThree";
import { useState, useRef } from "react";

export function SignUp() {
  const formPgRef= useRef(1);
  const [showForm, setShowForm] = useState(1);

  const shiftFormUp = () => {
    setShowForm(showForm +1);
    formPgRef.current++;
    console.log(formPgRef.current);
  }

  const shiftFormBack = () => {
    setShowForm(showForm -1);
    formPgRef.current--;
    console.log(formPgRef.current);
  }
  
  return(
    <div className="container">
      <div id="steps-section">
        <div id="step-one-line" className={"step-line-active"}>
          <span id="step-one-dot" className={"step-dot-active"}>Step One</span>
        </div>
        <div id="step-two-line" className={formPgRef.current >= 2 ? "step-line-active" : "step-line"}>
          <span id="step-two-dot" className={formPgRef.current >= 2 ? "step-dot-active" : "step-dot"}>Step Two</span>
        </div>
        <div id="step-three-line" className={formPgRef.current == 3 ? "step-line-active" : "step-line"}>
          <span id="step-three-dot" className={formPgRef.current == 3 ? "step-dot-active" : "step-dot"}>Step Three</span>
        </div>
      </div>
      <div style={{visibility: formPgRef.current == 1 ? "visible" : "hidden"}}><StepOne /></div>
      <StepTwo />
      <StepThree />
      <div className="button-container">
      <button className="form-btn" onClick={shiftFormBack}>Back</button>
      <button className="form-btn" onClick={shiftFormUp}>Next</button>
    </div>
    </div>
  )
}
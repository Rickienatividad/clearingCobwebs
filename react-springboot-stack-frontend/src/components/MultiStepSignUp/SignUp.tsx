import "./SignUp.css";
import classNames from "classnames";
import { StepOne } from "./StepOne";
import { StepTwo } from "./StepTwo";
import { useState } from "react";

export function SignUp() {
  const [isStepOneActive, setIsStepOneActive] = useState(true);
  
  return(
    <div className="container">
      <div id="step-one-line" className={isStepOneActive ? "step-line-active" : "step-line"}>
        <span id="step-one-dot" className={isStepOneActive ? "step-dot-active" : "step-dot"}>Step One</span>
      </div>
      <div id="step-two-line" className="step-line">
        <span id="step-two-dot" className="step-dot">Step Two</span>
      </div>
      <div id="step-three-line" className="step-line">
        <span id="step-three-dot" className="step-dot">Step Three</span>
      </div>
    </div>
  )
}
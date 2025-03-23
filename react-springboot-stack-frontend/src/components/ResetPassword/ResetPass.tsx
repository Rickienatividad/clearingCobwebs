import "./ResetPass.css";
import {useRef} from "react";
import axios from "axios";

export function ResetPass() {
  const emailAddressRef = useRef<HTMLInputElement>(null);

  const handleRetrieveSecurityQuestion = (event: React.FormEvent) => {
    event.preventDefault();
    //axios.get()
  }

  return(
    <>
    <h3>Enter Your Email Address</h3>
    <div className="resetPassFormGroup">
      <form onSubmit={handleRetrieveSecurityQuestion}>
        <input type="email" name="email" placeholder="Email Address" className="form-control-lg" id="reset-email-input" ref={emailAddressRef}/>
        <p id="not-found">Email Not Found</p>
        <button type="submit">Enter</button>
      </form>
    </div>
    </>
  )
}
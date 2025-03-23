import "./ResetPass.css";
import {useRef} from "react";
import axios from "axios";

export function ResetPass() {
  const emailAddressRef = useRef<HTMLInputElement>(null);

  const handleSendResetToken = (event: React.FormEvent) => {
    event.preventDefault();
    const resetPasswordObj: object = {
      email: emailAddressRef.current?.value
    }
    console.log(resetPasswordObj);
    axios.post("http://localhost:8080/users/reset/password", resetPasswordObj)
    .then((response) => {
      console.log(response)
    })
    .catch((error) => {
      console.log(error.status)
    })
  }

  return(
    <>
    <h3>Enter Your Email Address</h3>
    <div className="resetPassFormGroup">
      <form onSubmit={handleSendResetToken}>
        <input type="email" name="email" placeholder="Email Address" className="form-control-lg" id="reset-email-input" ref={emailAddressRef}/>
        <p id="not-found">Email Not Found</p>
        <button type="submit">Enter</button>
      </form>
    </div>
    </>
  )
}
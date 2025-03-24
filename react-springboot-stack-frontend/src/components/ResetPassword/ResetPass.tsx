import "./ResetPass.css";
import {useRef, useState} from "react";
import axios from "axios";

export function ResetPass() {
  const emailAddressRef = useRef<HTMLInputElement>(null);
  const tokenRef = useRef<HTMLInputElement>(null);
  const newPassRef = useRef<HTMLInputElement>(null);
  
  const[isUserFound, setIsUserFound] = useState(true);
  const[resetStage, setResetStage] = useState("email");

  const handleSendResetToken = (event: React.FormEvent) => {
    event.preventDefault();
    setIsUserFound(true);
    const resetPasswordObj: object = {
      email: emailAddressRef.current?.value
    }
    axios
    .post("http://localhost:8080/users/reset/password", resetPasswordObj)
    .then((response) => {
      console.log(response);
      setResetStage("token");
    })
    .catch((error) => {
      console.log(error.status)
      setIsUserFound(false);
    })
  }

  const handleCheckToken = (event: React.FormEvent) => {
    event.preventDefault();
    const checkTokenRequestObj: object = {
      email: emailAddressRef.current?.value,
      resetToken: tokenRef.current?.value
    }
    axios
      .post("http://localhost:8080/users/check-token", checkTokenRequestObj)
      .then((response) => {
        console.log(response);
        if(response.request.status == 200){
          setResetStage("newPass")
        }
      })
      .catch((error) => {
        console.log(error)
      })
  }

  const handleUpdatePassword = (event: React.FormEvent) => {
    event.preventDefault();
    const updatePasswordObj: object = {
      email: emailAddressRef.current?.value, 
      resetToken: tokenRef.current?.value, 
      newPassword: newPassRef.current?.value
    }

    axios
    .post("http://localhost:8080/users/update-password", updatePasswordObj)
    .then((response) => {
      console.log(response)
    })
  }

  return(
    <>
    <h2>FormState = {resetStage}</h2>
    <h3>Enter Your Email Address</h3>
    <div className="form-group" style={{display: resetStage.startsWith("email") ? "block" : "none"}}>
      <form onSubmit={handleSendResetToken}>
        <input type="email" name="email" placeholder="Email Address" className="form-control-lg" id="reset-email-input" ref={emailAddressRef}/>
        <p className="not-found" style={{visibility: isUserFound ? "hidden" : "visible"}}>Email Not Found</p>
        <button type="submit">Enter</button>
      </form>
    </div>
    <div className="form-group" style={{display: resetStage.startsWith("token") ? "block" : "none"}}>
      <form onSubmit={handleCheckToken}>
        <input type="text" className="form-control-lg" placeholder="Enter Reset Code" ref={tokenRef}/>
        <button type="submit">Enter</button>
      </form>  
    </div>
    <div className="form-group" style={{display: resetStage.startsWith("newPass") ? "block" : "none"}}>
      <form onSubmit={handleUpdatePassword}>
        <input type="password" className="form-control-lg" placeholder="Enter New Password" ref={newPassRef}/>
        <input type="password" className="form-control-lg" placeholder="Confirm New Password"/>
        <button type="submit">Enter</button>
      </form>  
    </div>
    </>
  )
}
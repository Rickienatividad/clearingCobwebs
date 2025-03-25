import "./ResetPass.css";
import {useEffect, useRef, useState} from "react";
import axios from "axios";

export function ResetPass() {
  const emailAddressRef = useRef<HTMLInputElement>(null);
  const tokenRef = useRef<HTMLInputElement>(null);
  const newPassRef = useRef<HTMLInputElement>(null);
  const formStageRef = useRef<string>("Enter Your Email Address");
  
  const[isUserFound, setIsUserFound] = useState(true);
  const[formStage, setFormStage] = useState("email");

  const handleSendResetToken = (event: React.FormEvent) => {
    event.preventDefault();
    setIsUserFound(true);
    const resetPasswordObj: object = {
      email: emailAddressRef.current?.value
    }
    axios
    .post("http://localhost:8080/users/reset/password", resetPasswordObj)
    .then((response) => {
      if(response.request.status == 200){
        formStageRef.current = "A Code Has Been Sent to Your Email. Enter it Here.";
        setFormStage("code");
      }
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
        if(response.request.status == 200){
          formStageRef.current = "Enter a New Password";
          setFormStage("newPass");
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

  useEffect(() => {
    console.log(formStage);
  }, [formStage])

  return(
    <>
    <h3>{formStageRef.current}</h3>
    <div className="form-group" style={{display: formStageRef.current.startsWith("Enter Your") ? "block" : "none"}}>
      <form onSubmit={handleSendResetToken}>
        <input type="email" name="email" placeholder="Email Address" className="form-control-lg" id="reset-email-input" ref={emailAddressRef}/>
        <p className="not-found" style={{visibility: isUserFound ? "hidden" : "visible"}}>Email Not Found</p>
        <button type="submit">Enter</button>
      </form>
    </div>
    <div className="form-group" style={{display: formStageRef.current.includes("Code") ? "block" : "none"}}>
      <form onSubmit={handleCheckToken}>
        <input type="text" className="form-control-lg" placeholder="Enter Reset Code" ref={tokenRef}/>
        <button type="submit">Submit Code</button>
      </form>  
    </div>
    <div className="form-group" style={{display: formStageRef.current.includes("Password") ? "block" : "none"}}>
      <form onSubmit={handleUpdatePassword}>
        <input type="password" className="form-control-lg" placeholder="Enter New Password" ref={newPassRef}/>
        <input type="password" className="form-control-lg" placeholder="Confirm New Password"/>
        <button type="submit">Update Password</button>
      </form>  
    </div>
    </>
  )
}
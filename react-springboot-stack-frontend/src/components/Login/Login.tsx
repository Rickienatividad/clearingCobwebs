import { NavLink } from 'react-router'
import './Login.css'
import { FormEvent, useState } from 'react'
import axios from 'axios';

export function Login() {

  const [isLoginValid, setIsLoginValid]= useState(true);

  const handleLogin = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const params: FormData = new FormData(e.currentTarget);
    const loginData = Object.fromEntries(params);
    const loginObj: object = {
      email: loginData.email,
      password: loginData.password
    }

    axios
      .post("http://localhost:8080/auth", loginObj)
      .then((response) => {
        console.log(response);
        setIsLoginValid(true);
      })
      .catch((error) => {
        //console.log(error.status)
        if(error.status == "401") {setIsLoginValid(false)}
      })
  }
  return(
    <>
    <h3>Enter Credentials to Log In</h3>
    <div className="form-group">
      <form onSubmit={handleLogin}>
        <input type="text" name="email" placeholder="email" className="form-control-lg"/>
        <input type="password" name="password" placeholder="password" className="form-control-lg"/>
        <div hidden={isLoginValid} style={{marginTop: '-1.2rem'}}>
          <h5 style={{color:'#a62a17'}}>Password was incorrect.</h5>
        </div>
        <button type="submit">Enter</button>
      </form>
      <div style={{textAlign: 'right', marginTop: '1.3rem', marginRight: '-5.5rem'}}>
        <NavLink to="/ResetPassword">Forgot Password?</NavLink>
      </div>
      <div style={{textAlign: 'right', marginRight: '-5.5rem'}}>
        <p style={{display:'inline', marginRight: '1rem'}}> Not a member?</p>
        <NavLink to="/SignUp">Sign Up!</NavLink>
      </div>
    </div>
    </>
  )
}
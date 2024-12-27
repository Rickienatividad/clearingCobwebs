import { NavLink } from 'react-router'
import './Login.css'

export function Login() {
  return(
    <>
    <h3>Login</h3>
    <div className="form-group">
      <form action="">
        <input type="text" name="email" placeholder="email" className="form-control-lg"/>
        <input type="password" name="password" placeholder="password" className="form-control-lg"/>
        <button>Enter</button>
      </form>
      <div style={{textAlign: 'right', marginTop: '1.3rem', marginRight: '-5.5rem'}}>
        <p style={{display:'inline', marginRight: '1rem'}}> Not a member?</p>
        <NavLink to="/">Sign Up!</NavLink>
      </div>
    </div>
    <div>
      <h5 style={{color:'#a62a17'}}>Password was incorrect.</h5>
    </div>
    </>
  )
}
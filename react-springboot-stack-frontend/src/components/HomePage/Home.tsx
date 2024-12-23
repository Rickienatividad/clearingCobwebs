import "./Home.css"
import FormInput from "../FormInputs/FormTextInput"

export function Home() {
  return (
    <>
    <h3>Sign Up Today!</h3>
    <div id="main-container" className="form-group form-container">
      <form action="">
        <div>
          <input name="email" type="text" className="form-control-lg" placeholder="Email Address"/>
        </div>
        <div>
          <input name="password" type="password" className="form-control-lg" placeholder="Password"/>
          <div id="password-help-block" className="pw-help-text">
            Must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
          </div>
        </div>
        <div>
          <input name="password-confirmation" type="password" className="form-control-lg" placeholder="Confirm Password"/>
        </div>
        <div id="password-confirmation-help-block" className="pw-help-text">
            Passwords must match
          </div>
        <div className="btn-group">
          <select name="security-question" id="security-question">
            <option value="">Choice 1</option>
          </select>
        </div>
        <div>
          <input name="security-answer" type="security-answer" className="form-control-lg" placeholder="Provide Answer to Selected Security Question"/>
        </div>
      </form>
      <button className="continue-btn">Continue</button>
    </div>
    </>
  )
}
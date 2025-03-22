import { BrowserRouter, Routes, Route } from 'react-router'

import './App.css'
import 'bootstrap/dist/css/bootstrap.css'
import { Home } from './components/OldSignUpForm/Home'
import { SignUp } from './components/MultiStepSignUp/SignUp'
import { Login } from './components/Login/Login'
function App() {

  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/Sign-Up" element={<Home/>} />
        <Route path="/SignUp" element={<SignUp/>} />
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App

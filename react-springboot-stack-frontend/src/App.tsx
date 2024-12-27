import { BrowserRouter, Routes, Route } from 'react-router'

import './App.css'
import 'bootstrap/dist/css/bootstrap.css'
import { Home } from './components/HomePage/Home'
import { Login } from './components/Login/Login'
function App() {

  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App

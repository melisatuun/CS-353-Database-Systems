import { Button, TextField, Snackbar } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/LoginPage.css";

export default function LoginPage() {
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [loginFailed, setLoginFailed] = useState(false);
    const navigate = useNavigate();
    
    function handleLogin(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/user/login", {
            username: username,
            password: password
        }).then((res) => {
            sessionStorage.setItem('username', JSON.stringify(username));
            navigate("/profile");
        }).catch((err) => {
            setLoginFailed(true);
            console.log(err);
        });
    }

    return (
        <div className="LoginPage">
            Proceed to log into DMGTV:
            <form noValidate autoComplete="off" onSubmit={(e) => {handleLogin(e);}} style={{marginTop: "10%"}}>
                <TextField label="Username" onChange={(e) => {setUsername(e.target.value);}}/>
                <TextField label="Password" type="password" onChange={(e) => {setPassword(e.target.value);}}/>
                <Button className="LoginPageButton" type="submit" style={{ marginTop: "1.5%" }}>Log In</Button>
            </form>
            <Snackbar open={loginFailed} autoHideDuration={2000} message="Credentials for login are wrong!" onClose={() => {setLoginFailed(false);}}/>
        </div>
    );
}
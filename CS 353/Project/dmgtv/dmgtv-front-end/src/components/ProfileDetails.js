import "../css/ProfilePage.css"
import React, {useEffect, useState} from "react";
import TextField from '@mui/material/TextField';
import {Button} from "@mui/material";
import {AdapterDateFns} from '@mui/x-date-pickers/AdapterDateFns';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import axios from "axios";

export default function ProfileDetails() {
    const [fullname, setFullname] = useState("");
    const [password, setPassword] = useState("");
    const [birthdate, setBirthdate] = useState(null);
    const [editedFullname, setEditedFullname] = useState("");
    const [editedPassword, setEditedPassword] = useState("");
    const [editedBirthdate, setEditedBirthdate] = useState(null);

    useEffect(() => {
        (async function () {
            try {
                const response = await axios.get('http://localhost:8080/user/get/' + JSON.parse(sessionStorage.getItem("username")));
                setFullname(response.data.data.fullName);
                setPassword(response.data.data.password);
                setBirthdate(response.data.data.birthDate);
                setEditedFullname(response.data.data.fullName);
                setEditedPassword(response.data.data.password);
                setEditedBirthdate(response.data.data.birthDate);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    function handleFullname(e) {
        e.preventDefault();
        let date = new Date(birthdate);
        axios.post("http://localhost:8080/user/update/" + JSON.parse(sessionStorage.getItem("username")), {
            password: password,
            fullName: editedFullname,
            year: date.getFullYear(),
            month: date.getMonth(),
            date: date.getDate()
        }).then((res) => {
            setFullname(editedFullname);
        }).catch((err) => {
            console.log(err);
        });
    }

    function handlePassword(e) {
        e.preventDefault();
        let date = new Date(birthdate);
        axios.post("http://localhost:8080/user/update/" + JSON.parse(sessionStorage.getItem("username")), {
            password: editedPassword,
            fullName: fullname,
            year: date.getFullYear(),
            month: date.getMonth(),
            date: date.getDate()
        }).then((res) => {
            setPassword(editedPassword);
        }).catch((err) => {
            console.log(err);
        });
    }

    function handleBirthdate(e) {
        e.preventDefault();
        let date = new Date(editedBirthdate);
        axios.post("http://localhost:8080/user/update/" + JSON.parse(sessionStorage.getItem("username")), {
            password: password,
            fullName: fullname,
            year: date.getFullYear(),
            month: date.getMonth(),
            date: date.getDate()
        }).then((res) => {
            setBirthdate(editedBirthdate);
        }).catch((err) => {
            console.log(err);
        });
    }

    return (
        <LocalizationProvider dateAdapter={AdapterDateFns}>
            <div className="ProfileDetails">
                Edit profile details
                <div>
                    <TextField disabled label="Username" defaultValue={JSON.parse(sessionStorage.getItem("username"))} style={{marginTop: "5%"}}/>
                </div>
                <form noValidate autoComplete="off" onSubmit={(e) => {handlePassword(e);}} style={{marginTop: "5%"}}>
                    <TextField label="Password" type="password" value={editedPassword} onChange={(e) => {setEditedPassword(e.target.value);}}/>
                    <Button type="submit">Change password</Button>
                </form>
                <form noValidate autoComplete="off" onSubmit={(e) => {handleFullname(e);}} style={{marginTop: "5%"}}>
                    <TextField label="Full name" value={editedFullname} onChange={(e) => {setEditedFullname(e.target.value);}}/>
                    <Button type="submit">Change full name</Button>
                </form>
                <form noValidate autoComplete="off" onSubmit={(e) => {handleBirthdate(e);}} style={{marginTop: "5%"}}>
                    <DesktopDatePicker label="Birth date" value={editedBirthdate} renderInput={(params) => <TextField {...params}/>} onChange={(e) => {setEditedBirthdate(e);}} style={{marginTop: "5%"}}/>
                    <Button type="submit">Change birth date</Button>
                </form>
            </div>
        </LocalizationProvider>
    );
}

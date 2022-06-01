import { AppBar, CssBaseline, Toolbar } from "@mui/material";
import { Link } from "react-router-dom";

export default function UserNavbar() {
    return (
        <AppBar position="static">
            <CssBaseline/>
            <Toolbar>
                <Link to="/movies" style={{margin: "25px 50px", color: "white"}}>
                    Movies
                </Link>
                <Link to="/profile" style={{margin: "25px 50px", color: "white"}}>
                    Profile
                </Link>
                <Link to="/friends" style={{margin: "25px 50px", color: "white"}}>
                    Friends
                </Link>
                <Link to="/mymovies" style={{margin: "25px 50px", color: "white"}}>
                    Go to my movies
                </Link>
                <Link to="/" style={{margin: "25px 50px", color: "white"}} onClick={() => {sessionStorage.clear();}}>
                    Log out
                </Link>
            </Toolbar>
        </AppBar>
    );
}
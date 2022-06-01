import { Button, Card, CardContent, Dialog, List, ListItem, Paper, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import BoughtMovie from "./BoughtMovie";
import RentedMovie from "./RentedMovie";
import UserNavbar from "./UserNavbar";
import axios from "axios";

export default function MyMoviesPage() {
    const [rentedMovies, setRentedMovies] = useState([]);
    const [boughtMovies, setBoughtMovies] = useState([]);
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/rent/getMovies/" + JSON.parse(sessionStorage.getItem("username")));
                setRentedMovies(response.data.data);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/review/user/" + JSON.parse(sessionStorage.getItem("username")));
                setReviews(response.data.data);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/buy/getAllMovies/" + JSON.parse(sessionStorage.getItem("username")));
                setBoughtMovies(response.data.data);
                console.log(response.data.data)
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    return (
        <div>
            <UserNavbar/>
            <Typography variant="h4" component="div" style={{marginTop: "2.5%", marginBottom: "2.5%"}}>
                Rented movies
            </Typography>
            <List>
                {rentedMovies.map((movie, index) => (
                    <ListItem key={index}>
                        <RentedMovie id={movie.id} title={movie.title} productionYear={movie.productionYear}/>
                    </ListItem>
                ))}
            </List>
            <Typography variant="h4" component="div" style={{marginTop: "2.5%", marginBottom: "2.5%"}}>
                Bought movies
            </Typography>
            <List>
                {boughtMovies.map((movie, index) => (
                    <ListItem key={index}>
                        <BoughtMovie id={movie.id} title={movie.title} productionYear={movie.productionYear}/>
                    </ListItem>
                ))}
            </List>
            <Typography variant="h5" component="div" style={{marginTop: "2.5%", marginBottom: "2.5%"}}>
                My movie reviews
            </Typography>
            <List>
                {reviews.map((review, index) => (
                    <ListItem key={index}>
                        <Paper variant="outlined">
                            <Typography style={{margin: "2.5%"}}>
                                <strong>Movie:</strong> {review.movie.title}
                            </Typography>
                            <Typography style={{margin: "2.5%"}}>
                                <strong>Rating:</strong> {review.rating} / 5
                            </Typography>
                            <Typography style={{margin: "2.5%"}}>
                                <strong>Comment:</strong> {review.comment}
                            </Typography>
                        </Paper>
                    </ListItem>
                ))}
            </List>
        </div>
    );
}
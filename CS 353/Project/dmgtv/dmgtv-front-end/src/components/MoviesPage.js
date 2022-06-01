import MUIDataTables from "mui-datatables";
import { Button, Switch, Snackbar, Dialog, List, ListItem, Paper, Typography } from "@mui/material";
import UserNavbar from "./UserNavbar";
import { useState, useEffect } from "react";
import axios from "axios";

export default function MoviesPage() {
    const [movies, setMovies] = useState([]);
    const [rentSuccessful, setRentSuccessful] = useState(false);
    const [buySuccessful, setBuySuccessful] = useState(false);
    const [wishSuccessful, setWishSuccessful] = useState(false);
    const [seeReviews, setSeeReviews] = useState(false);
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/movie/read");
                console.log(response.data);
                setMovies(response.data);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    function rentMovie(title) {
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const rentURL = "http://localhost:8080/rent/rentMovie/" + title + "/" + currentUser;
        axios.post(rentURL).then((res) => {
            setRentSuccessful(true);
        }).catch((err) => {
            console.log(err);
            setRentSuccessful(false);
        });
    }

    function buyMovie(title) {
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const rentURL = "http://localhost:8080/buy/buyMovie/" + title + "/" + currentUser;
        axios.post(rentURL).then((res) => {
            setBuySuccessful(true);
        }).catch((err) => {
            console.log(err);
            setBuySuccessful(false);
        });
    }

    function seeMovieReviews(title) {
        let movieId;
        for (let i in movies) {
            if (movies[i].title === title) {
                movieId = movies[i].id;
            }
        }
        const reviewURL = "http://localhost:8080/review/movie/" + movieId;
        axios.get(reviewURL).then((res) => {
            setReviews(res.data.data);
            setSeeReviews(true);
        }).catch((err) => {
            console.log(err);
        });
    }

    function addToWishlist(title) {
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const wishlistURL = "http://localhost:8080/wish/createWish/" + title + "/" + currentUser;
        axios.post(wishlistURL).then((res) => {
            setWishSuccessful(true);
        }).catch((err) => {
            console.log(err);
            setWishSuccessful(false);
        });
    }

    const columns = [
        {
            name: "title",
            label: "Title",
            options: {
                filter: false,
                sort: true
            }
        },
        {
            name: "productionYear",
            label: "Production Year",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "rating",
            label: "Rating",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "pricePerMonth",
            label: "Price Per Month",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "priceToBuy",
            label: "Price To Buy",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "ageRestricted",
            label: "Age restricted",
            options: {
                filter: true,
                sort: true,
                customBodyRender: (value, tableMeta, updateValue) => {
                    return (
                        <div>
                            <Switch checked={value}/>
                        </div>
                    );
                }
            }
        },
        {
            name: "imdbRating",
            label: "IMDB Rating",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "likeCount",
            label: "Like count",
            options: {
                filter: true,
                sort: true
            }
        },
        {
            name: "actions",
            label: "Actions",
            options: {
                filter: false,
                customBodyRender: (value, tableMeta, updateValue) => {
                    return (
                        <div>
                            <Button variant="outlined" onClick={() => {rentMovie(tableMeta.rowData[0]);}}>
                                Rent
                            </Button>
                            <Button variant="outlined" onClick={() => {buyMovie(tableMeta.rowData[0]);}}>
                                Buy
                            </Button>
                            <Button variant="outlined" onClick={() => {seeMovieReviews(tableMeta.rowData[0]);}}>
                                See reviews
                            </Button>
                            <Button variant="outlined" onClick={() => {addToWishlist(tableMeta.rowData[0]);}}>
                                Add to wishlist
                            </Button>
                        </div>
                    );
                }
            }
        }
    ];

    const options = {download: false, print: false, selectableRows: "none", filterType: "checkbox"};

    return (
        <div>
            <UserNavbar/>
            <MUIDataTables title="Movie List" data={movies} columns={columns} options={options}/>
            <Snackbar open={rentSuccessful} autoHideDuration={2000} message="Movie rented successfully!" onClose={() => {setRentSuccessful(false);}}/>
            <Snackbar open={buySuccessful} autoHideDuration={2000} message="Movie bought successfully!" onClose={() => {setBuySuccessful(false);}}/>
            <Snackbar open={wishSuccessful} autoHideDuration={2000} message="Movie added to wishlist successfully!" onClose={() => {setWishSuccessful(false);}}/>
            <Dialog open={seeReviews} onClose={() => {setSeeReviews(false);}}>
                <List>
                    {reviews.map((review, index) => (
                        <ListItem key={index}>
                            <Paper variant="outlined">
                                <Typography style={{margin: "2.5%"}}>
                                    <strong>Reviewed by:</strong> {review.user.username}
                                </Typography>
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
            </Dialog>
        </div>
    );
}
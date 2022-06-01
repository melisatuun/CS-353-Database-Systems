import { Button, TextField, Rating, Card, CardContent, Dialog, DialogTitle, List, ListItem, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";

export default function BoughtMovie(props) {
    const [rating, setRating] = useState(5);
    const [reviewMovieDialogOpen, setReviewMovieDialogOpen] = useState(false);
    const [review, setReview] = useState("");
    const [editedReview, setEditedReview] = useState("");

    function reviewMovie(e) {
        e.preventDefault();
        axios.post("http://localhost:8080/review/add", {
            username: JSON.parse(sessionStorage.getItem("username")),
            movieId: props.id,
            rating: rating,
            comment: editedReview
        }).then((res) => {
            setReviewMovieDialogOpen(false);
        }).catch((err) => {
            console.log(err);
        });
    }

    return (
        <div>
            <Card>
                <CardContent>
                    <Typography>
                        {props.title}
                    </Typography>
                    <Typography>
                        {props.productionYear}
                    </Typography>
                </CardContent>
            </Card>
            <Button onClick={() => {setReviewMovieDialogOpen(true);}}>
                    Review movie
            </Button>
            <Dialog open={reviewMovieDialogOpen} onClose={() => {setReviewMovieDialogOpen(false);}}>
                <DialogTitle style={{margin: "2.5%"}}>
                    Review {props.title}
                </DialogTitle>
                <form noValidate autoComplete="off" onSubmit={(e) => {reviewMovie(e);}}>
                    <Rating size="large" defaultValue={5} onChange={(event, newValue) => {setRating(newValue);}}/>
                    <TextField multiline label="Comment" defaultValue={review} onChange={(e) => {setEditedReview(e.target.value);}}/>
                    <Button type="submit">Send review</Button>
                </form>
            </Dialog>
        </div>
    );
}
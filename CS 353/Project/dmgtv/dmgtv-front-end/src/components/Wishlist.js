import "../css/ProfilePage.css"
import React, {useEffect, useState } from "react";
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import axios from "axios";

const myWishlist = [{"id": 0, "title": "Godfather"}, {"id": 1, "title": "Showshank Redemption"}, {"id": 2, "title": "Pulp Fiction"}, {"id": 3, "title": "Matrix"}];

export default function Wishlist() {
    const [wishlist, setWishlist] = useState([]);

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/wish/readWish/" + JSON.parse(sessionStorage.getItem("username")));
                console.log(response.data.data);
                setWishlist(response.data.data);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    function removeMovie(wish) {
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const wishlistURL = "http://localhost:8080/wish/delete/" + wish.movie.title + "/" + currentUser;
        axios.delete(wishlistURL).then((res) => {
            let index = wishlist.indexOf(wish);
            setWishlist(wishlist.slice(0, index).concat(wishlist.slice(index + 1)));
        }).catch((err) => {
            console.log(err);
        });

    }

    return (
        <div className="Wishlist">
            Wishlist
            <List>
                {wishlist.map((wishlist, key) => (
                    <ListItem key={key} secondaryAction={
                        <IconButton edge="end" aria-label="delete" onClick={() => {removeMovie(wishlist);}}>
                            <DeleteIcon/>
                        </IconButton>}>
                        <ListItemText primary={wishlist.movie.title}/>
                    </ListItem>))
                }
            </List>
        </div>
    );
}
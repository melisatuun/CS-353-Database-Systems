import { Button, List, ListItem, ListItemText, TextField, Snackbar } from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";

export default function FriendList() {
    const [addFriendUnsuccessful, setAddFriendUnsuccessful] = useState(false);
    const [friends, setFriends] = useState([]);
    const [friendUsername, setFriendUsername] = useState();

    useEffect(() => {
        (async function() {
            try {
                const response = await axios.get("http://localhost:8080/friend/getFriends/" + JSON.parse(sessionStorage.getItem("username")));
                console.log(response.data.data);
                setFriends(response.data.data);
            }
            catch (error) {
                console.log(error);
            }
        })();
    }, []);

    function removeFriend(friend) {
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const friendURL = "http://localhost:8080/friend/delete/" + currentUser + "/" + friend.username;
        axios.post(friendURL).then((res) => {
            let index = friends.indexOf(friend);
            setFriends(friends.slice(0, index).concat(friends.slice(index + 1)));
        }).catch((err) => {
            console.log(err);
        });
    }

    function addFriend(e) {
        e.preventDefault();
        const currentUser = JSON.parse(sessionStorage.getItem("username"));
        const friendURL = "http://localhost:8080/friend/create/" + currentUser + "/" + friendUsername;
        axios.post(friendURL).then((res) => {
            setAddFriendUnsuccessful(false);
            setFriends([...friends, {"username": friendUsername}]);
        }).catch((err) => {
            console.log(err);
            setAddFriendUnsuccessful(true);
        });
    }

    return (
        <div>
            Friend List
            <List style={{maxHeight: '500px', overflow: 'auto', marginBottom: "5%"}}>
                {friends.map((friend, index) => (
                    <ListItem key={index}>
                        <ListItemText primary={friend.username}/>
                        <Button onClick={() => {removeFriend(friend);}}>Remove friend</Button>
                    </ListItem>
                    ))
                }
            </List>
            Add a friend
            <form noValidate autoComplete="off" onSubmit={(e) => {addFriend(e);}}>
                <TextField label="Friend's username" onChange={(e) => {setFriendUsername(e.target.value);}}></TextField>
                <Button type="submit">Add friend</Button>
            </form>
            <Snackbar open={addFriendUnsuccessful} autoHideDuration={2000} message="Unable to add the specified friend!" onClose={() => {setAddFriendUnsuccessful(false);}}/>
        </div>
    );
}

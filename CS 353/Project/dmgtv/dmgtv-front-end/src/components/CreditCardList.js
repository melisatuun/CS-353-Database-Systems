import "../css/ProfilePage.css"
import React, {useState } from "react";
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import CreditCard from "./CreditCard"
import Button from "@mui/material/Button";
import { Dialog, TextField, DialogTitle } from "@mui/material";

export default function CreditCardList(props) {
    const [cards, setCards] = useState(props.cards);
    const [dialogOpen, setDialogOpen] = useState(false);
    const [newCardnumber, setNewCardNumber] = useState();
    const [newExpirationdate, setNewExpirationDate] = useState();
    const [newCvv, setNewCvv] = useState();

    function addCard(e) {
        e.preventDefault();
        setCards([...cards, {"card_number": newCardnumber, "expiration_date": newExpirationdate, "cvv": newCvv}]);
        setDialogOpen(false);
    }

    return (
        <div className="CreditCardList">
            Credit cards
            <List>
                {cards.map((card, index) => (
                    <ListItem key={index}>
                        <CreditCard card_number={card.card_number} expiration_date={card.expiration_date} cvv={card.cvv}/>
                    </ListItem>
                ))}
            </List>
            <Button onClick={() => {setDialogOpen(true);}}>Add a new credit card</Button>
            <Dialog open={dialogOpen} onClose={() => {setDialogOpen(false);}}>
                <DialogTitle>
                    Enter new credit card's information
                </DialogTitle>
                <form noValidate autoComplete="off" onSubmit={(e) => {addCard(e)}}>
                    <TextField label="Credit card number" onChange={(e) => {setNewCardNumber(e.target.value)}}/>
                    <TextField label="Expiration date" onChange={(e) => {setNewExpirationDate(e.target.value)}}/>
                    <TextField label="CVV" onChange={(e) => {setNewCvv(e.target.value)}}/>
                    <Button type="submit">Update details</Button>
                </form>
            </Dialog>         
        </div>
    );
}
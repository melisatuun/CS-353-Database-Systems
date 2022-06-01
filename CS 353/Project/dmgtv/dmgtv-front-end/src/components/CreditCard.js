import "../css/ProfilePage.css"
import React, {useState } from "react";
import {Button, Card, CardContent, Dialog, Typography, TextField, DialogTitle} from "@mui/material";

export default function CreditCard(props) {
    const [cardnumber, setCardNumber] = useState(props.card_number);
    const [expirationdate, setExpirationDate] = useState(props.expiration_date);
    const [cvv, setCvv] = useState(props.cvv);
    const [editedCardnumber, setEditedCardNumber] = useState();
    const [editedExpirationdate, setEditedExpirationDate] = useState();
    const [editedCvv, setEditedCvv] = useState();
    const [dialogOpen, setDialogOpen] = useState(false);

    function handleCCN(e) {
        setEditedCardNumber(e);
    }

    function handleExp(e) {
        setEditedExpirationDate(e);
    }

    function handleCVV(e) {
        setEditedCvv(e);
    }

    function editCreditCard(e) {
        e.preventDefault();
        setCardNumber(editedCardnumber);
        setExpirationDate(editedExpirationdate);
        setCvv(editedCvv);
        setDialogOpen(false);
    }

    return (
        <div className="CreditCard">
            <Card>
                <CardContent>
                    <Typography>
                        {cardnumber}
                    </Typography>
                    <Typography>
                        {expirationdate}
                    </Typography>
                </CardContent>
            </Card>
            <Button onClick={() => {setDialogOpen(true);}}>Edit credit card info</Button>
            <Dialog open={dialogOpen} onClose={() => {setDialogOpen(false);}}>
                <DialogTitle>
                    Edit credit card info
                </DialogTitle>
                <form noValidate autoComplete="off" onSubmit={(e) => {editCreditCard(e)}}>
                    <TextField label="Credit card number" defaultValue={cardnumber} onChange={(e) => {handleCCN(e.target.value)}}/>
                    <TextField label="Expiration date"defaultValue={expirationdate} onChange={(e) => {handleExp(e.target.value)}}/>
                    <TextField label="CVV" defaultValue={cvv} onChange={(e) => {handleCVV(e.target.value)}}/>
                    <Button type="submit">Update details</Button>
                </form>
            </Dialog>
        </div>
    );
}
import "../css/ProfilePage.css"
import React from "react";
import Wishlist from "./Wishlist";
import ProfileDetails from "./ProfileDetails";
import CreditCardList from "./CreditCardList";
import UserNavbar from "./UserNavbar";

const myCreditCards = [{"card_number": 1234567890123456, "expiration_date": "01/01/2000", "cvv": 123}, {"card_number": 1000000000000000, "expiration_date": "01/01/1991", "cvv": 999}]; 

export default function ProfilePage() {
    return (
        <div className="ProfilePage">
            <UserNavbar/>
            <Wishlist/>
            <ProfileDetails/>
            <CreditCardList cards={myCreditCards}/>
        </div>
    );
}

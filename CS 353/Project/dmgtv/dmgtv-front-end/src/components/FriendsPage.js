import axios from "axios";
import { useEffect, useState } from "react";
import FriendList from "./FriendList";
import UserNavbar from "./UserNavbar";

export default function FriendsPage() {

    return (
        <div>
            <UserNavbar/>
            <FriendList/>
        </div>
    );
}
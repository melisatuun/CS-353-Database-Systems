import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './index.css';
import App from './App';
import ProfilePage from './components/ProfilePage';
import FriendsPage from './components/FriendsPage';
import EmployeePage from './components/EmployeePage';
import MoviesPage from './components/MoviesPage';
import MyMoviesPage from './components/MyMoviesPage';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<App/>}/>
        <Route path='/profile' element={<ProfilePage/>}/>
        <Route path='/friends' element={<FriendsPage/>}/>
        <Route path='/employee' element={<EmployeePage/>}/>
        <Route path='/movies' element={<MoviesPage/>}/>
        <Route path='/mymovies' element={<MyMoviesPage/>}/>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

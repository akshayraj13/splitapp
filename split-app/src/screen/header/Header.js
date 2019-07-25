import React from "react";
import './Header.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import logo from '../../assets/images/logo.png'

class Header extends React.Component {
    render() {
        const isLoggedIn = false;
        return (

            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

                <a className="navbar-brand" href="https://www.alacriti.com/">
                    <img src={logo}
                         width="45"
                         height="45" title="Alacriti Logo"
                         alt="Alacriti Logo"></img>
                </a>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        {isLoggedIn &&
                        <li className="nav-item active">
                            <a className="nav-link" href="http://localhost:4200/home"><span>Home</span> </a>
                        </li>
                        }
                        {isLoggedIn &&
                        <li className="nav-item">
                            <h4 style={{color:'azure'}} id="fixedtitle">Welcome User </h4>
                        </li>
                        }

                        <li className="nav-item">
                            <h4 id="fixedtitle"
                                >Welcome to Split
                                My Bill </h4>

                        </li>
                        <li id="fixedbutton">
                            <div id="login-btn">
                                <button className="btn btn-outline-success my-2 my-sm-0">Login</button>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
    }
export default Header
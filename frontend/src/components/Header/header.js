import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const Header = (props) => {
    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary fixed-top shadow-sm">
                <div className="container">
                    <Link className="navbar-brand" to="/accommodations">
                        <img src="/airbnb.png" alt="Logo" width="30" height="30" className="d-inline-block align-top me-2"/>
                        AirBnB
                    </Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarCollapse">
                        <ul className="navbar-nav ms-auto">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" to="/accommodations">Accommodations</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/categories">Categories</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;

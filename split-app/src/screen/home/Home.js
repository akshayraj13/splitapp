import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

import Books from './Urlobject'
import CreateLink from "./CreateLink";



class Home extends React.Component {
    render() {
        return (
            <div className="container" style= {{marginTop:'40px'}}>
                <div className="row" style= {{marginTop:'20px'}}>
                    <div className="span8 columns">
                        <h1 style= {{marginTop:'15px'}}>What is SplitMyBill?</h1>
                        <p>
                            SplitMyBill is a Providence, RI based company that makes it easy
                            to split bills with friends and family. We organize all your shared
                            expenses and IOUs in one place, so that everyone can see who they owe.
                            Whether you are sharing a ski vacation, splitting rent with roommates,
                            or owe someone for lunch, Splitwise makes life easier. We store data “in
                            the cloud,” so that you can access it anywhere: on iPhone, Android, or
                            on your computer.
                        </p>
                        <h2 style= {{marginTop:'20px'}}>We focus on fairness</h2>
                        <p>
                            Most people want to be fair to each other, but sometimes they
                            forget, or can’t decide on what fair is. In addition to helping people
                            honor their debts, we provide mediation advice about fairness issues
                            through our “fairness calculators.” These calculators turn our
                            crowdsourced data into a neutral fairness opinion about your personal
                            situation.
                        </p>
                    </div>

                </div>
                <nav className="navbar">
                    <a className="navbar-brand" href="">
                        GraphQL in React - Demo application
                    </a>
                </nav>
                <div className="container">
                    <Books />
                    <CreateLink/>
                </div>
            </div>
        );
    }
}



export default Home
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {BrowserRouter as Router, Link, Route} from "react-router-dom";
import Header from "./screen/header/Header";
import Home from "./screen/home/Home";
import Books from "./screen/home/Urlobject";
import {ApolloProvider} from "react-apollo";
import ApolloClient from "apollo-boost";
import Login from "./screen/Login/Login";
import Card from "./screen/card/Card";

class Square extends React.Component {
    render() {
        return (
            <button className="square">
                {/* TODO */}
            </button>
        );
    }
}

class Board extends React.Component {
    renderSquare(i) {
        return <Square />;
    }

    render() {
        const status = 'Next player: X';

        return (
            <div>
                <div className="status">{status}</div>
                <div className="board-row">
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
            </div>
        );
    }
}

class Game extends React.Component {
    render() {
        return (
            <div className="game">
                <div className="game-board">
                    <Board />
                </div>
                <div className="game-info">
                    <div>{/* status */}</div>
                    <ol>{/* TODO */}</ol>
                </div>
            </div>
        );
    }
}

class App extends React.Component {
    render(){
        return (
            <div>
                <Home/>
            </div>



        );
    }

}
class About extends React.Component {
    render(){
        return (
            <div>
                <h2>About</h2>
                ...
            </div>
        );
    }

}
const client = new ApolloClient({
    uri: 'http://localhost:8080/graphql',
})
// ========================================

ReactDOM.render(
    <ApolloProvider client={client}>
        <Router>
            <Header/>
            <main>
                <Route exact path="/" component={App} />
                <Route path="/about" component={About} />
                <Route path="/login" component={Login} />
                <Route path="/card" component={Card} />
            </main>
        </Router>
    </ApolloProvider>,
    document.getElementById('root')
);

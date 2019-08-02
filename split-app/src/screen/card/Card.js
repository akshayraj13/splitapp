import React, { Component } from "react";
import { Button, FormGroup, FormControl } from "react-bootstrap";
import Form from "react-bootstrap/Form";
import  gql  from 'graphql-tag';
import {Mutation} from "react-apollo";


const ADD_CARD = gql`
  mutation signinUser($auth: AuthData) {
    signinUser(auth: $auth) {
      token
    }
  }
`

export default class Card extends Component {
    constructor(props) {
        super(props);

        this.state = {
            cardholdername: "",
            cardnumber: "",
            cardname: "",
            month: "",
            year: "",
            cvv: "",
            email: "",
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
        console.log('event',event.target.value,event.target.name);
        // this.setState({
        //     [event.target.id]: event.target.value
        // });
    }

    handleSubmit = event => {
        console.log(this.state)
        event.preventDefault();
    }

    render() {
        return (
            <Mutation mutation={ADD_CARD} variables={{auth: this.state}}
                      onCompleted={data =>{
                          console.log(data);
                      }}>
                {(addTodo, { data }) => (
                        <Form className="credit-card" onSubmit={e => {
                            e.preventDefault();
                            addTodo();
                        }}>
                            <div className="form-header">
                                <h4 className="title">Credit card detail</h4>
                            </div>

                            <div className="form-body">

                                    <input type="text" name="cardholdername" onChange={this.handleChange} className="card-number" style= {{textTransform:'uppercase'}}
                                       placeholder="Card Holder Name"/>
                                    <input type="number" name="cardnumber" onChange={this.handleChange} className="card-number" placeholder="Card Number"/>
                                    <input type="text" name="cardname" onChange={this.handleChange} className="card-number" placeholder="Card Name"/>
                                    <div class="date-field">
                                                <div class="month">
                                                    <select onChange={this.handleChange}  name="month">

                                                        <option selected disabled>Month</option>
                                                        <option value="january">January</option>
                                                        <option value="february">February</option>
                                                        <option value="march">March</option>
                                                        <option value="april">April</option>
                                                        <option value="may">May</option>
                                                        <option value="june">June</option>
                                                        <option value="july">July</option>
                                                        <option value="august">August</option>
                                                        <option value="september">September</option>
                                                        <option value="october">October</option>
                                                        <option value="november">November</option>
                                                        <option value="december">December</option>
                                                    </select>
                                                </div>
                                                <div class="year">
                                                    <select onChange={this.handleChange} name="year">
                                                        <option selected disabled value=''>Year</option>
                                                        <option value="2019">2019</option>
                                                        <option value="2020">2020</option>
                                                        <option value="2021">2021</option>
                                                        <option value="2022">2022</option>
                                                        <option value="2023">2023</option>
                                                        <option value="2024">2024</option>
                                                        <option value="2025">2025</option>
                                                        <option value="2026">2026</option>
                                                        <option value="2027">2027</option>
                                                        <option value="2028">2028</option>
                                                        <option value="2029">2029</option>
                                                        <option value="2030">2030</option>

                                                    </select>
                                                </div>
                                    </div>
                                    <div className="card-verification">
                                                <div className="cvv-input">
                                                    <input type="password" name="cvv" onChange={this.handleChange} maxLength="3" name="cvv" placeholder="CVV"/>
                                                </div>
                                                <div className="cvv-details" >
                                                    <p>3 digits usually found <br/> on the signature strip</p>
                                                </div>
                                    </div>

                                     <button type="submit" className="proceed-btn" >Proceed</button>

                                    <button type="button" className="proceed-btn" style={{color: 'rgb(254, 254, 255)'}}>View Card</button>
                            </div>

                        </Form>
                )}
            </Mutation>
        );

    }
}

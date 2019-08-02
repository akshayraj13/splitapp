import React, { Component } from "react";
import { Button, FormGroup, FormControl } from "react-bootstrap";
import "./Login.css";
import Form from "react-bootstrap/Form";
import  gql  from 'graphql-tag';
import {Mutation} from "react-apollo";


const LOGIN_MUTATION = gql`
  mutation signinUser($auth: AuthData) {
    signinUser(auth: $auth) {
      token
    }
  }
`

export default class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: ""
        };
    }

    validateForm() {
        return this.state.email.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
        this.setState({
            [event.target.id]: event.target.value
        });
    }

    handleSubmit = event => {
        console.log(this.state)
        event.preventDefault();
    }

    render() {
        return (
            <Mutation mutation={LOGIN_MUTATION} variables={{auth: this.state}}
                      onCompleted={data =>{
                          console.log(data);
                      }}>
                {(addTodo, { data }) => (
                    <div className="Login">
                        <Form onSubmit={e => {
                            e.preventDefault();
                            addTodo();
                        }}>
                            <FormGroup controlId="email" bsSize="large">
                                <label>Email</label>
                                <FormControl
                                    autoFocus
                                    type="email"
                                    value={this.state.email}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <FormGroup controlId="password" bsSize="large">
                                <label>Password</label>
                                <FormControl
                                    value={this.state.password}
                                    onChange={this.handleChange}
                                    type="password"
                                />
                            </FormGroup>
                            <Button
                                block
                                bsSize="large"
                                disabled={!this.validateForm()}
                                type="submit"
                            >
                                Login
                            </Button>
                        </Form>
                    </div>
                )}
            </Mutation>
        );

    }
}

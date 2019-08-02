import React from 'react'
import { Query } from 'react-apollo';
import  gql  from 'graphql-tag';

var loading =0;
var error = 0;
var data= 0;



const Books = () => (
    <div>
        <Query
            query={gql`
      {
        allLinks{
            url
            description
	    }
      }
    `}
        >
            {({ loading, error, data }) => {
                if (loading) {loading=1;return <p></p>};
                if (error) {error=1;return <p></p>};
                return data.allLinks.map(({ url, description }) => (
                    <div >
                        <p>{`${url} by ${description}`}</p>
                    </div>
                ));
            }}
        </Query>

    </div>


)

export default Books
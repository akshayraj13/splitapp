import React from 'react'
import { Query } from 'react-apollo';
import  gql  from 'graphql-tag';




const Books = () => (
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
            if (loading) return <p>Loading...</p>;
            if (error) return <p>Error :(</p>;
            return data.allLinks.map(({ url, description }) => (
                <div >
                    <p>{`${url} by ${description}`}</p>
                </div>
            ));
        }}
    </Query>
)

export default Books
import React, { Fragment } from 'react';
import { createGlobalStyle } from 'styled-components';
import Github from './home/Github';
import Router from './Router';

const App = () => {
	return (
		<Fragment>
			<GlobalStyle />
			<Github />
			<Router />
		</Fragment>
	);
};

export default App;

const GlobalStyle = createGlobalStyle`
    @import url('https://fonts.googleapis.com/css?family=Anton|Comfortaa&display=swap');
    
    body {
        margin: 0;
        padding: 0;
        overflow-x: hidden;
        min-height: 100vh;
        background-color: #6b48ff;
    }

    h1, h2, h3, h4, h5 {
        margin: 0;
        padding: 0;
        margin-bottom: 1rem;
        color: #444444;
        letter-spacing: 1px;
        font-weight: 400;
        font-family: 'Anton', sans-serif;
    }

    p, button, span, input {
        margin: 0;
        padding: 0;
        font-family: 'Comfortaa', cursive;
    }

    p {
        line-height: 1.6;
    }

    a {
        text-decoration: none;
    }

    .no-select {
        -webkit-touch-callout: none; /* iOS Safari */
          -webkit-user-select: none; /* Safari */
           -khtml-user-select: none; /* Konqueror HTML */
             -moz-user-select: none; /* Firefox */
              -ms-user-select: none; /* Internet Explorer/Edge */
                  user-select: none; /* Non-prefixed version, currently
                                        supported by Chrome and Opera */
      }
`;

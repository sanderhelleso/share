import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import Home from './home/Home';
import Download from './download/Download';

const Router = () => (
	<BrowserRouter>
		<Switch>
			<Route exact path="/" component={Home} />
			<Route exact path="/download/:shareCode" component={Download} />
		</Switch>
	</BrowserRouter>
);

export default Router;

import React from 'react';
import ReactDOM from 'react-dom';

import App from './App';
import * as serviceWorker from './serviceWorker';

import './css/index.css';

// Default index code so that the DOM knows what to render at the root.
ReactDOM.render(<App />, document.getElementById('root'));

serviceWorker.unregister();

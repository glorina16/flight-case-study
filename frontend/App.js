import React, { Component } from 'react';
import FareSearch from './components/FareSearch.jsx';
class App extends Component{
   render(){
      return(
        <div className="container">
          <div className="hw-block hw-block--mb hw-block--mt">
            <div className="hw-block hw-block--mb hw-block--mt">
              <h1 className="hw-h1">KLM Flight Search</h1>
            </div>
          <div className="hw-block">
            <FareSearch />
          </div>
          </div>
          
      </div>
      
      );
   }
}
export default App;
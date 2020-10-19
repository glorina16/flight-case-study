import React, { Component } from 'react';
import FareSearch from './components/FareSearch.jsx';
import AirportsList from './components/AirportsList.jsx';

class App extends Component{

    constructor(props){
        super(props);
        this.state = {
            show : 'showSearch'
        }
        this.showComponent = this.showComponent.bind(this)
    }

    showComponent(e){

        const {value} = e.target;
        if(value == 'showAirports')
        {
            this.setState({show  : value})
        }
        else{
            this.setState({show  : value})
        }
    }

   render(){
      return(
        <div className="container">
          <div className="hw-block hw-block--mb hw-block--mt">
            <div className="hw-block hw-block--mb hw-block--mt">
              <h1 className="hw-h1">KLM Flight Search</h1>
            </div>

          <div className="hw-block hw-block--mb hw-block--mt">
          <label class="hw-radio-button">
            <input type="radio" name="showSearch"  checked={this.state.show === "showSearch"} value="showSearch" onChange={(e) => this.showComponent(e)} />
            <span class="hw-radio-button__label">Show Fares</span>
            <i class="hw-radio-button__indicator"></i>
          </label>

          <label class="hw-radio-button">
            <input type="radio" name="showAirports" checked={this.state.show === "showAirports"} value="showAirports" onChange={(e) =>this.showComponent(e)}  />
            <span class="hw-radio-button__label">Show Airports</span>
            <i class="hw-radio-button__indicator"></i>
          </label>
          </div>
          <div className="hw-block">
            {this.state.show == 'showAirports' ? <AirportsList /> : <FareSearch /> }
           </div>
          </div>
      </div>
      
      );
   }
}
export default App;
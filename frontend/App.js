import React, { Component } from 'react';
import FareSearch from './components/FareSearch.jsx';
import AirportsList from './components/AirportsList.jsx';
import Statistics from './components/Statistics.jsx';

class App extends Component{

    constructor(props){
        super(props);
        this.state = {
            show : 'showSearch',
            showStats: false
        }
        this.showComponent = this.showComponent.bind(this)
    }

    showComponent(e){

        const {value} = e.target;
        if(value == 'showAirports')
        {
            this.setState({show  : value})
            this.setState({showStats : false})
        }
        else if(value=='showStats'){
            this.setState({showStats : true})
            this.setState({show  : ''})
        }
        else{
            this.setState({show  : value})
             this.setState({showStats : false})
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
          <label class="hw-radio-button">
              <input type="radio" name="showStats" checked={this.state.showStats} value="showStats" onChange={(e) =>this.showComponent(e)}  />
              <span class="hw-radio-button__label">Show Statistics</span>
              <i class="hw-radio-button__indicator"></i>
            </label>
          </div>
          <div className="hw-block">
            {this.state.show == 'showAirports' ? <AirportsList />  :  null }
            {this.state.show == 'showSearch' ? <FareSearch />  :  null }
            {this.state.showStats? <Statistics/> : null}
           </div>

          </div>
      </div>
      
      );
   }
}
export default App;
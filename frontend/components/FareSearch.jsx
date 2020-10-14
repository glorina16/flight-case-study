import React, {PureComponent} from 'react';
import './FareSearch.css';
import {FormGroup, FormControl, Button} from 'react-bootstrap';

export default class extends PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            origin : '',
            destination : '',
            price : '',
            originAirports:[],
            destinationAirports:[],
            loading:false
        }
        this.onOriginChange = this.onOriginChange.bind(this);
        this.onDestinationChange = this.onDestinationChange.bind(this);
        this.onSearchFlightFare = this.onSearchFlightFare.bind(this);
    }

      getAirportList(term,airport) {        
        fetch(`http://localhost:9000/airports?locale=en&&term=${term}`)
          .then(response => response.json())
          .then(data => airport == "originAirports" ? this.setState({originAirports: data._embedded.locations}): this.setState({destinationAirports: data._embedded.locations}))
      }

      fetchFare() {
          this.setState({loading : true})
          this.setState({price : ''});
        fetch(`http://localhost:9000/fares/${this.state.origin}/${this.state.destination}?currency=EUR`)
          .then(response => response.json())
          .then(data =>{

            this.setState({price : data.amount});
            this.setState({loading : false}) 
          }
          )
      }


    onOriginChange(e) {
        this.setState({ origin: e.target.value })
        if(e.target.value.length>=1){
            
            this.getAirportList(e.target.value,"originAirports")
        }
        
    }

    onDestinationChange(e) {
        
        this.setState({ destination: e.target.value })
        if(e.target.value.length>=1){
        this.getAirportList(e.target.value,"destinationAirports")
        }
    }   

    onSearchFlightFare(event) {
        event.preventDefault();
        this.fetchFare()
    }

    selectAirportOrigin(air){
        this.setState({origin :  air.code})
        this.setState({originAirports : []})
        
    }
    selectAirportDestination(air){
        this.setState({destination :  air.code})
        this.setState({ destinationAirports: []})
        
    }

    render() {
    let originList = this.state.originAirports.length > 0 ? this.state.originAirports.map((air,i) => <li key={i} onClick={()=>this.selectAirportOrigin(air)}className="hw-search__suggestion pd" data-hw-search-suggestion="true">{air.code}</li>) : [] 

    let destinationList = this.state.destinationAirports.length > 0 ? this.state.destinationAirports.map((air,i) => <li key={i} onClick={()=>this.selectAirportDestination(air)}className="hw-search__suggestion pd" data-hw-search-suggestion="true">{air.code}</li>) : [] 
        return (
            <>
            <form onSubmit={this.onSearchFlights}>
                <span className="hw-search text" data-hw-search data-hw-search-placeholders='["Origin"]'>
                    <div className="hw-search__inner">
                        <input
                            data-hw-search-input
                            className="hw-search__input" type="text"
                            placeholder="Enter Origin"
                    
                            value={this.state.origin}
                            onChange={this.onOriginChange}
                        />
                        <span className="hw-search__placeholder" data-hw-search-placeholder></span>
                    </div>
                    <ul className="hw-search__suggestions">
                        {originList}
                    </ul>
                </span>
                
                <span className="hw-search text" data-hw-search data-hw-search-placeholders='["Destination"]'>
                    <div className="hw-search__inner">
                        <input
                            data-hw-search-input
                            className="hw-search__input" type="text"
                            placeholder="Enter Destination"
                            value={this.state.destination}
                            onChange={this.onDestinationChange}
                        />
                        <span className="hw-search__placeholder" data-hw-search-placeholder></span>
                    </div>
                    <ul className="hw-search__suggestions">
                        {destinationList}
                    </ul>
                </span>
                <Button
                    type="submit"
                    onClick ={this.onSearchFlightFare}
                    className="btn"
                >
                  {'Find Fare'}
                </Button>
            </form>

                { this.state.price == '' && this.state.loading === false ? '' : this.state.price == '' && this.state.loading === true ? <h4 className="hw-h4 mt">Loading...</h4> : <span>
                    <h4 className="hw-h4 mt"> Price from {this.state.origin} to {this.state.destination}  is </h4> <h3 className="hw-h3">{this.state.price}EUR</h3> 
                </span>
             }
             
            </>
        );
    }
}

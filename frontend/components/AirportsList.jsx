import React from 'react';

class AirportsList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            airportsData :[],
            totalPages : '',
            pageNo : 1,
            searchTerm : ''
        }
        this.getNextAirportsData = this.getNextAirportsData.bind(this);
        this.getPrevAirportsData = this.getPrevAirportsData.bind(this);
    }

         getDetails(e) {
         this.setState({searchTerm :e.target.value })
                fetch(`http://localhost:9000/airports?locale=en&&term=${this.state.searchTerm}&&page=1`)
                  .then(response => response.json())
                  .then(data => this.setState({airportsData: data._embedded.locations}))


         }

      componentDidMount() {
        fetch(`http://localhost:9000/airports?locale=en&&term=en&&page=${this.state.pageNo}`)
          .then(response => response.json())
          .then(data => {
            console.log(data)
            this.setState({airportsData: data._embedded.locations})
            this.setState({totalPages : data.page.totalPages})
          })
      }

      getPrevAirportsData(){
      if(this.state.pageNo == 1){
            return
        }
        this.setState((prev,next) => ({ pageNo : prev.pageNo - 1 }))
        fetch(`http://localhost:9000/airports?locale=en&&term=en&&page=${this.state.pageNo}`)
              .then(response => response.json())
              .then(data => {
                this.setState({airportsData: data._embedded.locations})
              })
      }

      getNextAirportsData(){
       if(this.state.pageNo == this.state.totalPages){
              return
            }
        this.setState((prev,next) => ({ pageNo : prev.pageNo + 1 }))
        fetch(`http://localhost:9000/airports?locale=en&&term=en&&page=${this.state.pageNo}`)
                  .then(response => response.json())
                  .then(data => {
                    this.setState({airportsData: data._embedded.locations})
            })
      }

    render() {
    let airports = this.state.airportsData.length > 0 ? this.state.airportsData.map(airport => <tr><td>{airport.parent.name}</td><td>{airport.parent.description}</td></tr>) :''

        return (
            <>
                <div className="hw-block hw-block--px hw-block--pt hw-block--pb hw-background-color-primary-lighter">
                  <label className="hw-label">
                    Search Airport
                    <input className="hw-input hw-input--white" value={this.state.searchTerm} type="search" placeholder="Search"  onChange={(e)=>this.getDetails(e)}/>
                  </label>
                </div>
                <div>
                    <div className="hw-table">
                      <table>

                        <thead>
                          <tr>
                            <th>Name</th>
                            <th>Description</th>
                          </tr>
                        </thead>

                        <tbody>
                            {airports}
                        </tbody>
                      </table>
                    </div>
                </div>
                <div>
                <button  onClick =  {() => this.getPrevAirportsData()} disabled ={this.state.pageNo == 1 ? true :false} >Previous</button>
                <button onClick = {() => this.getNextAirportsData()} disabled ={this.state.pageNo == this.state.totalPages ? true :false}>Next</button>
                </div>
            </>
        );
    }
}


export default AirportsList;
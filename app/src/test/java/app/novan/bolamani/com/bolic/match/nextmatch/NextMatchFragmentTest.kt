package app.novan.bolamani.com.bolic.match.nextmatch

import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.model.EventResponse
import app.novan.bolamani.com.bolic.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchFragmentTest{

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter= NextMatchPresenter(view,apiRepository,gson,TestContextProvider())
    }

    @Test
    fun getNextMatchList(){

        val event: MutableList<Event> = mutableListOf()
        val response = EventResponse(event)

        val idLeague = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getListNextMatch(idLeague)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getMatchNextList(idLeague)

        verify(view).showLoading()
        if (event.isNotEmpty()){
            Mockito.verify(view).showMatchList(response.events)
            Mockito.verify(view).hideLoading()
        }

    }
}
import Vue from 'vue'
import router from 'vue-router';
import viewReservation from '../components/viewReservation';
import reserveTheField from '../components/ReserveTheField';

Vue.use(router);

export default new router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: reserveTheField
        },
        {
            path: '/viewreserve',
            component: viewReservation
        }
    ]
});
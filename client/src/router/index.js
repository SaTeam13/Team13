import Vue from 'vue'
import router from 'vue-router';
import viewReservation from '../components/viewReservation';
import reserveTheField from '../components/ReserveTheField';
import Borrow from '../components/Borrow';
import Customer from '../components/Customer';
import ViewCustomer from '../components/ViewCustomer';
import Employee from '../components/Employee';
import Login from '../components/Login';
Vue.use(router);

export default new router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '/',
        component: Login
        },
        {
            path: '/borrow',
            component: Borrow
        },
        {
            path: '/reservation',
            component: reserveTheField
        },
        {
            path: '/viewreserve',
            component: viewReservation
        },
        {
            path: '/customer',
            component: Customer
        },
        {
            path: '/viewcustomer',
            component: ViewCustomer
        },
        {
            path: '/employee',
            component: Employee
        },
        
    ]
});
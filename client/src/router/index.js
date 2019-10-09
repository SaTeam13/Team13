import Vue from 'vue'
import router from 'vue-router';
import viewReservation from '../components/viewReservation';
import reserveTheField from '../components/ReserveTheField';
import Borrow from '../components/Borrow';
import Customer from '../components/Customer';
import viewCustomer from '../components/viewCustomer';
import Employee from '../components/Employee';
import PaymentMember from '../components/PaymentMember';
import viewPayment from '../components/viewPayment';
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
            component: viewCustomer
        },
        {
            path: '/employee',
            component: Employee
        },
        {
            path: '/paymentmember',
            component: PaymentMember
        },
        {
            path: '/viewpayment',
            component: viewPayment
        },
        
    ]
});
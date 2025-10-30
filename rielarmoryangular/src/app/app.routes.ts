import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: "",
        pathMatch: "full",
        redirectTo: "/home",
    },
    {
        path: "home",
        loadComponent: () => {
            return import("./pages/home/home.component").then(m => m.HomeComponent);
        }  
    },
    {
        path: "legal",
        loadComponent: () => {
            return import("./pages/legal/legal.component").then(m => m.LegalComponent);
        }
    },
    {
        path: "store",
        loadComponent: () => {
            return import("./pages/store/store.component").then(m => m.StoreComponent);
        }
    },
    {
        path: "checkout",
        loadComponent: () => {
            return import("./pages/checkout/checkout.component").then(m => m.CheckoutComponent);
        }
    }
];

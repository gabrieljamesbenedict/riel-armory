import { Routes } from '@angular/router';
import { AuthGuard } from './guard/auth.guard';

export const routes: Routes = [

    {
        path: "login",
        loadComponent: () => import("./pages/login/login.component").then(m => m.LoginComponent)
    },

    {
        path: "",
        pathMatch: "full",
        redirectTo: "/home",
    },

    {
        path: "home",
        loadComponent: () => import("./pages/home/home.component").then(m => m.HomeComponent),
        canActivate: [AuthGuard]
    },

    // Legal routes are public
    {
        path: "legal",
        loadComponent: () => import("./pages/legal/legal.component").then(m => m.LegalComponent),
        canActivate: [AuthGuard]
    },
    {
        path: "legal/definition-of-terms",
        loadComponent: () => import("./pages/legal/definition-of-terms/definition-of-terms.component").then(m => m.DefinitionOfTermsComponent)
    },
    {
        path: "legal/licenses-and-permits",
        loadComponent: () => import("./pages/legal/licenses-and-permits/licenses-and-permits.component").then(m => m.LicensesAndPermitsComponent)
    },
    {
        path: "legal/eligibility-requirements",
        loadComponent: () => import("./pages/legal/eligibility-requirements/eligibility-requirements.component").then(m => m.EligibilityRequirementsComponent)
    },
    {
        path: "legal/steps-to-apply",
        loadComponent: () => import("./pages/legal/steps-to-apply/steps-to-apply.component").then(m => m.StepsToApplyComponent)
    },

    // Protected routes
    {
        path: "store",
        loadComponent: () => import("./pages/store/store.component").then(m => m.StoreComponent),
        canActivate: [AuthGuard]
    },

    {
        path: "checkout",
        loadComponent: () => import("./pages/checkout/checkout.component").then(m => m.CheckoutComponent),
        canActivate: [AuthGuard]
    }
];

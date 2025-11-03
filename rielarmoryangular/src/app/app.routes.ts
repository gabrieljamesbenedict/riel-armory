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
        path: "legal/definition-of-terms",
        loadComponent: () => {
            return import("./pages/legal/definition-of-terms/definition-of-terms.component").then(m => m.DefinitionOfTermsComponent)
        }
    },
    {
        path: "legal/licenses-and-permits",
        loadComponent: () => {
            return import("./pages/legal/licenses-and-permits/licenses-and-permits.component").then(m => m.LicensesAndPermitsComponent)
        }
    },
    {
        path: "legal/eligibility-requirements",
        loadComponent: () => {
            return import("./pages/legal/eligibility-requirements/eligibility-requirements.component").then(m => m.EligibilityRequirementsComponent)
        }
    },
    {
        path: "legal/steps-to-apply",
        loadComponent: () => {
            return import("./pages/legal/steps-to-apply/steps-to-apply.component").then(m => m.StepsToApplyComponent)
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

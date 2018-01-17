webpackJsonp([9],{

/***/ "../../../../../src/app/layout/step/step-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__step_component__ = __webpack_require__("../../../../../src/app/layout/step/step.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StepRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_2__step_component__["a" /* StepComponent */] }
];
var StepRoutingModule = (function () {
    function StepRoutingModule() {
    }
    return StepRoutingModule;
}());
StepRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forChild(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */]]
    })
], StepRoutingModule);

//# sourceMappingURL=step-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/layout/step/step.component.html":
/***/ (function(module, exports) {

module.exports = "\n<div [@routerTransition]>\n    <app-page-header [heading]=\"'Step List'\" [icon]=\"'fa-table'\"></app-page-header>\n  \t\n\t<div class=\"row\" style=\"margin-top: 50px;\">\n\t\n\t  <div class=\"col col-xl-12 col-lg-12\">\n            <div class=\"card mb-3\">\n                <div class=\"card-header\">Step Rows\n                     <a [routerLink]=\"['/stepedit/'+ caseId +'/0']\" [routerLinkActive]=\"['router-link-active']\"  style=\"float: right;\">add</a>\n                </div>\n                <div class=\"card-block table-responsive\">\n                    <table class=\"table table-hover table-striped\">\n                        <thead>\n                        <tr>\n                            <th>name</th>\n                            <th>description</th>\n                            <th>stepNo</th>\n                            <th>sequence</th>\n                            <th>takePhoto</th>\n                            <th>elementName</th>\n                            <th>elementValue</th>\n                            <th>elementSeq</th>\n                            <th>waitTime</th>\n                            <th>getType</th>\n                            <th>doType</th> \n                            <th>excptValue</th>\n                            <th>url</th>\n                            <th>success</th>\n                            <th>image</th>\n                            <th>Edit</th>\n                            <th>Delete</th>\n                        </tr>\n                        </thead>\n                        <tbody>\n                        <tr *ngFor=\"let step of steps let i = index\">\n                            <td> {{step.stepName}}</td>\n                            <td >{{step.stepDesc}}</td>\n                            <td >{{step.stepNo}}</td>\n                            <td >{{step.sequence}}</td>\n                            <td >{{step.takePhoto}}</td>\n                            <td >{{step.elementName}}</td>\n                            <td >{{step.elementValue}}</td>\n                            <td >{{step.elementSeq}}</td>\n                            <td >{{step.waitTime}}</td>\n                            <td >{{step.getType}}</td>\n                            <td >{{step.doType}}</td>\n                            <td >{{step.excptValue}}</td>  \n                            <td >{{step.url}}</td>  \n                            <td >{{step.success}}</td>  \n                            <td ><a href=\"{{baseurl+step.image}}\" target=\"_Blank\"><img *ngIf=\"step.image != null && step.image != ''\" src=\"{{baseurl+step.image}}\" alt=\"img\" width=\"150\" height=\"100\"></a></td>  \n                            <td><a [routerLink]=\"['/stepedit/'+ caseId +'/'+ step.stepId]\" [routerLinkActive]=\"['router-link-active']\" >edit</a></td>\n                            <td><a href=\"javascript:void(0);\" (click)=\"delete(step.stepId)\">Delete</a></td>\n                        </tr>\n                        </tbody>\n                    </table>\n                </div>\n            </div>\n             <div class=\"row\" class=\"form-group\" style=\"margin-top: 10px;text-align: center;\">\n\t        <button type=\"button\" class=\"btn btn-secondary\"  (click)=\"back()\">Back to Case</button>\n\t        </div>\n        </div>\n       \n\t</div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/layout/step/step.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__router_animations__ = __webpack_require__("../../../../../src/app/router.animations.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__ = __webpack_require__("../../../../@ngx-translate/core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return StepComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var StepComponent = (function () {
    function StepComponent(layoutService, translate, route, router) {
        this.layoutService = layoutService;
        this.translate = translate;
        this.route = route;
        this.router = router;
        this.loading = false;
        this.steps = [];
        this.browserLang = 'en';
    }
    StepComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.browserLang = this.translate.getBrowserLang();
        this.route.params.subscribe(function (params) { return _this.caseId = params.caseId; });
        this.baseurl = this.layoutService.get_base_url() + '/project/';
        if (this.caseId != 0) {
            this.layoutService.get_steps(this.caseId).subscribe(function (res) {
                _this.steps = res;
            });
        }
    };
    StepComponent.prototype.delete = function (stepId) {
        if (confirm("delete it?")) {
            this.layoutService.delete_step_byid(stepId).subscribe(function (val) {
                console.log("POST call successful value returned in body", val);
            }, function (response) {
                alert("run delete test step in error");
            }, function () {
                location.reload();
                console.log("The POST observable is now completed.");
            });
        }
    };
    StepComponent.prototype.back = function () {
        var _this = this;
        this.layoutService.get_case_byid(this.caseId).subscribe(function (res) {
            // window.location.href = "/listcase/"+res.modul.modulId;
            _this.router.navigate(['listcase/' + res.modul.modulId]);
        });
    };
    return StepComponent;
}());
StepComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-module',
        template: __webpack_require__("../../../../../src/app/layout/step/step.component.html"),
        animations: [__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__router_animations__["a" /* routerTransition */])()]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__["c" /* TranslateService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__["c" /* TranslateService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object, typeof (_d = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* Router */]) === "function" && _d || Object])
], StepComponent);

var _a, _b, _c, _d;
//# sourceMappingURL=step.component.js.map

/***/ }),

/***/ "../../../../../src/app/layout/step/step.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_interceptor_http_interceptor__ = __webpack_require__("../../../../../src/app/shared/interceptor/http.interceptor.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__step_component__ = __webpack_require__("../../../../../src/app/layout/step/step.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__step_routing_module__ = __webpack_require__("../../../../../src/app/layout/step/step-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__shared__ = __webpack_require__("../../../../../src/app/shared/index.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StepModule", function() { return StepModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var StepModule = (function () {
    function StepModule() {
    }
    return StepModule;
}());
StepModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
            __WEBPACK_IMPORTED_MODULE_6__step_routing_module__["a" /* StepRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_7__shared__["b" /* PageHeaderModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClientModule */]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */],
            { provide: __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["b" /* HTTP_INTERCEPTORS */], useClass: __WEBPACK_IMPORTED_MODULE_3__shared_interceptor_http_interceptor__["a" /* MyHttpLogInterceptor */], multi: true }],
        declarations: [__WEBPACK_IMPORTED_MODULE_5__step_component__["a" /* StepComponent */]]
    })
], StepModule);

//# sourceMappingURL=step.module.js.map

/***/ })

});
//# sourceMappingURL=9.chunk.js.map
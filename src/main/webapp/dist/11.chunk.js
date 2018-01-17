webpackJsonp([11],{

/***/ "../../../../../src/app/layout/module/module-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__module_component__ = __webpack_require__("../../../../../src/app/layout/module/module.component.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModuleRoutingModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    { path: '', component: __WEBPACK_IMPORTED_MODULE_2__module_component__["a" /* ModuleComponent */] }
];
var ModuleRoutingModule = (function () {
    function ModuleRoutingModule() {
    }
    return ModuleRoutingModule;
}());
ModuleRoutingModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */].forChild(routes)],
        exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* RouterModule */]]
    })
], ModuleRoutingModule);

//# sourceMappingURL=module-routing.module.js.map

/***/ }),

/***/ "../../../../../src/app/layout/module/module.component.html":
/***/ (function(module, exports) {

module.exports = "<div [@routerTransition]>\n    <app-page-header [heading]=\"'Module List'\" [icon]=\"'fa-table'\"></app-page-header>\n    <div class=\"row\">\n        <div class=\"col col-xl-10 col-lg-12\">\n            <div class=\"card mb-3\">\n                <div class=\"card-header\">Module Rows\n                 <a [routerLink]=\"['/moduleedit/'+ projectId+'/0']\" [routerLinkActive]=\"['router-link-active']\"  style=\"float: right;\">add</a>\n                </div>\n                <div class=\"card-block table-responsive\">\n                    <table class=\"table table-hover table-striped\">\n                        <thead>\n                        <tr>\n                            <th></th>\n                            <th>name</th>\n                            <th>description</th>\n                            <th>excuteDate</th>\n                            <th>Test Case</th>\n                            <th>Edit</th>\n                            <th>Delete</th>\n                            <th>Run Test</th>\n                            <th>Result</th>\n                        </tr>\n                        </thead>\n                        <tbody>\n                        <tr *ngFor=\"let module of modules let i = index\">\n                            <td>{{i+1}}</td>\n                            <td> {{module.modulName}}</td>\n                            <td>{{module.modulDesc}}</td>\n                             <td>{{module.excuteDate | date:'yyyy-MM-dd hh:mm:ss'}}</td>\n                            <td><a [routerLink]=\"['/listcase/'+module.modulId]\" [routerLinkActive]=\"['router-link-active']\" >case</a></td>\n                            <td><a [routerLink]=\"['/moduleedit/'+ projectId+'/'+module.modulId]\" [routerLinkActive]=\"['router-link-active']\" >edit</a></td>\n                            <td><a href=\"javascript:void(0);\" (click)=\"delete(module.modulId)\" >Delete</a></td>\n                            <td><a href=\"javascript:void(0);\" (click)=\"runTest(module.modulId)\">Run test</a></td>\n                            <td><a href=\"{{baseurl}}{{module.project.projectName}}/{{module.modulName}}.html\"  target=\"_Blank\">result</a></td>\n                        </tr>\n                        </tbody>\n                    </table>\n                </div>\n            </div>\n    \n        </div>\n    </div>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/layout/module/module.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__router_animations__ = __webpack_require__("../../../../../src/app/router.animations.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__ = __webpack_require__("../../../../@ngx-translate/core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ModuleComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ModuleComponent = (function () {
    function ModuleComponent(layoutService, translate, route) {
        this.layoutService = layoutService;
        this.translate = translate;
        this.route = route;
        this.modules = [];
        this.browserLang = 'en';
    }
    ModuleComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.browserLang = this.translate.getBrowserLang();
        this.baseurl = this.layoutService.get_base_url() + '/project/';
        this.route.params.subscribe(function (params) { return _this.projectId = params.projectId; });
        if (this.projectId != 0) {
            this.layoutService.get_modules(this.projectId).subscribe(function (res) {
                _this.modules = res;
            });
        }
    };
    ModuleComponent.prototype.runTest = function (moduleid) {
        this.layoutService.run_module_test(moduleid, true).subscribe(function (val) {
            console.log("POST call successful value returned in body", val);
            if (val.isrunOk == false) {
                alert(val.msg);
            }
            if (val.isdocOk == false) {
                alert("create document error");
            }
            if (val.isdocOk == true && val.isrunOk == true) {
                alert("test done!");
            }
        }, function (response) {
            alert("run module test in error");
        }, function () {
            console.log("The POST observable is now completed.");
        });
    };
    ModuleComponent.prototype.delete = function (caseId) {
        if (confirm("delete it?")) {
            this.layoutService.delete_module_byid(caseId).subscribe(function (val) {
                console.log("POST call successful value returned in body", val);
            }, function (response) {
                alert("run delete test step in error");
            }, function () {
                location.reload();
                console.log("The POST observable is now completed.");
            });
        }
    };
    return ModuleComponent;
}());
ModuleComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-module',
        template: __webpack_require__("../../../../../src/app/layout/module/module.component.html"),
        animations: [__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__router_animations__["a" /* routerTransition */])()]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__["c" /* TranslateService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__ngx_translate_core__["c" /* TranslateService */]) === "function" && _b || Object, typeof (_c = typeof __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_3__angular_router__["c" /* ActivatedRoute */]) === "function" && _c || Object])
], ModuleComponent);

var _a, _b, _c;
//# sourceMappingURL=module.component.js.map

/***/ }),

/***/ "../../../../../src/app/layout/module/module.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/@angular/common.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__shared_interceptor_http_interceptor__ = __webpack_require__("../../../../../src/app/shared/interceptor/http.interceptor.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__layout_service__ = __webpack_require__("../../../../../src/app/layout/layout.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__module_component__ = __webpack_require__("../../../../../src/app/layout/module/module.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__module_routing_module__ = __webpack_require__("../../../../../src/app/layout/module/module-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__shared__ = __webpack_require__("../../../../../src/app/shared/index.ts");
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ModuleModule", function() { return ModuleModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var ModuleModule = (function () {
    function ModuleModule() {
    }
    return ModuleModule;
}());
ModuleModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["NgModule"])({
        imports: [
            __WEBPACK_IMPORTED_MODULE_1__angular_common__["CommonModule"],
            __WEBPACK_IMPORTED_MODULE_6__module_routing_module__["a" /* ModuleRoutingModule */],
            __WEBPACK_IMPORTED_MODULE_7__shared__["b" /* PageHeaderModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClientModule */]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_4__layout_service__["a" /* LayoutService */],
            { provide: __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["b" /* HTTP_INTERCEPTORS */], useClass: __WEBPACK_IMPORTED_MODULE_3__shared_interceptor_http_interceptor__["a" /* MyHttpLogInterceptor */], multi: true }],
        declarations: [__WEBPACK_IMPORTED_MODULE_5__module_component__["a" /* ModuleComponent */]]
    })
], ModuleModule);

//# sourceMappingURL=module.module.js.map

/***/ })

});
//# sourceMappingURL=11.chunk.js.map
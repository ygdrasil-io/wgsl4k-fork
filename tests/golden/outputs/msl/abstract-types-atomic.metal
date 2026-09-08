#include <metal_stdlib>
using namespace metal;
struct Struct_6 {
    int old_value;
    char _pad0[4];
    bool exchanged;
};
struct Struct_8 {
    uint old_value;
    char _pad0[4];
    bool exchanged;
};

void test_atomic_i32() {
    atomicStore(&global_0, 1);
    atomicCompareExchangeWeak(&global_0, 1, 1);
    atomicCompareExchangeWeak(&global_0, 1, 1);
    atomicAdd(&global_0, 1);
    atomicSub(&global_0, 1);
    atomicAnd(&global_0, 1);
    atomicXor(&global_0, 1);
    atomicOr(&global_0, 1);
    atomicMin(&global_0, 1);
    atomicMax(&global_0, 1);
    atomicExchange(&global_0, 1);
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

Struct_6 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}

Struct_6 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, int arg_2) {
}

int atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

int atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

int atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

int atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

int atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

int atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

int atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

int atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

void test_atomic_u32() {
    atomicStore(&global_1, 1);
    atomicCompareExchangeWeak(&global_1, 1, 1u);
    atomicCompareExchangeWeak(&global_1, 1u, 1);
    atomicAdd(&global_1, 1);
    atomicSub(&global_1, 1);
    atomicAnd(&global_1, 1);
    atomicXor(&global_1, 1);
    atomicOr(&global_1, 1);
    atomicMin(&global_1, 1);
    atomicMax(&global_1, 1);
    atomicExchange(&global_1, 1);
}

void atomicStore(/* unknown type */ void arg_0, int arg_1) {
}

Struct_8 atomicCompareExchangeWeak(/* unknown type */ void arg_0, int arg_1, uint arg_2) {
}

Struct_8 atomicCompareExchangeWeak(/* unknown type */ void arg_0, uint arg_1, int arg_2) {
}

uint atomicAdd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicSub(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicAnd(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicXor(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicOr(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMin(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicMax(/* unknown type */ void arg_0, int arg_1) {
}

uint atomicExchange(/* unknown type */ void arg_0, int arg_1) {
}

[[kernel]]
void main(int global_0 [[buffer(0)]], uint global_1 [[buffer(1)]]) {
    test_atomic_i32();
    test_atomic_u32();
}

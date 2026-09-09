#version 450 core
precision highp float;
precision highp int;

struct Struct_6 {
    int old_value;
    bool exchanged;
};
struct Struct_8 {
    uint old_value;
    bool exchanged;
};
layout(set = 0, binding = 0) buffer int global_0;
layout(set = 0, binding = 1) buffer uint global_1;

void test_atomic_i32() {
    atomicStore(global_0, 1);
    atomicCompareExchangeWeak(global_0, 1, 1);
    atomicCompareExchangeWeak(global_0, 1, 1);
    atomicAdd(global_0, 1);
    atomicSub(global_0, 1);
    atomicAnd(global_0, 1);
    atomicXor(global_0, 1);
    atomicOr(global_0, 1);
    atomicMin(global_0, 1);
    atomicMax(global_0, 1);
    atomicExchange(global_0, 1);
}

void atomicStore(int arg_0, int arg_1) {
}

Struct_6 atomicCompareExchangeWeak(int arg_0, int arg_1, int arg_2) {
}

Struct_6 atomicCompareExchangeWeak(int arg_0, int arg_1, int arg_2) {
}

int atomicAdd(int arg_0, int arg_1) {
}

int atomicSub(int arg_0, int arg_1) {
}

int atomicAnd(int arg_0, int arg_1) {
}

int atomicXor(int arg_0, int arg_1) {
}

int atomicOr(int arg_0, int arg_1) {
}

int atomicMin(int arg_0, int arg_1) {
}

int atomicMax(int arg_0, int arg_1) {
}

int atomicExchange(int arg_0, int arg_1) {
}

void test_atomic_u32() {
    atomicStore(global_1, 1);
    atomicCompareExchangeWeak(global_1, 1, 1u);
    atomicCompareExchangeWeak(global_1, 1u, 1);
    atomicAdd(global_1, 1);
    atomicSub(global_1, 1);
    atomicAnd(global_1, 1);
    atomicXor(global_1, 1);
    atomicOr(global_1, 1);
    atomicMin(global_1, 1);
    atomicMax(global_1, 1);
    atomicExchange(global_1, 1);
}

void atomicStore(uint arg_0, int arg_1) {
}

Struct_8 atomicCompareExchangeWeak(uint arg_0, int arg_1, uint arg_2) {
}

Struct_8 atomicCompareExchangeWeak(uint arg_0, uint arg_1, int arg_2) {
}

uint atomicAdd(uint arg_0, int arg_1) {
}

uint atomicSub(uint arg_0, int arg_1) {
}

uint atomicAnd(uint arg_0, int arg_1) {
}

uint atomicXor(uint arg_0, int arg_1) {
}

uint atomicOr(uint arg_0, int arg_1) {
}

uint atomicMin(uint arg_0, int arg_1) {
}

uint atomicMax(uint arg_0, int arg_1) {
}

uint atomicExchange(uint arg_0, int arg_1) {
}

void wgsl_main() {
    test_atomic_i32();
    test_atomic_u32();
}

layout(local_size_x = 1, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_main();
}

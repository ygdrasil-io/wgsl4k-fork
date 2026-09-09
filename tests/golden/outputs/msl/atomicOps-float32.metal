#include <metal_stdlib>
using namespace metal;
struct Struct_3 {
    float atomic_scalar;
    array<float, 2> atomic_arr;
};

[[kernel]]
void cs_main(uint3 id [[thread_position_in_threadgroup]], float global_0 [[buffer(0)]], array<float, 2> global_1 [[buffer(1)]], Struct_3 global_2 [[buffer(2)]]) {
    atomicStore(&global_0, 1.5f);
    atomicStore(&global_1[1], 1.5f);
    atomicStore(&global_2.atomic_scalar, 1.5f);
    atomicStore(&global_2.atomic_arr[1], 1.5f);
    workgroupBarrier();
    float local_0 = atomicLoad(&global_0);
    float local_1 = atomicLoad(&global_1[1]);
    float local_2 = atomicLoad(&global_2.atomic_scalar);
    float local_3 = atomicLoad(&global_2.atomic_arr[1]);
    workgroupBarrier();
    atomicAdd(&global_0, 1.5f);
    atomicAdd(&global_1[1], 1.5f);
    atomicAdd(&global_2.atomic_scalar, 1.5f);
    atomicAdd(&global_2.atomic_arr[1], 1.5f);
    workgroupBarrier();
    atomicExchange(&global_0, 1.5f);
    atomicExchange(&global_1[1], 1.5f);
    atomicExchange(&global_2.atomic_scalar, 1.5f);
    atomicExchange(&global_2.atomic_arr[1], 1.5f);
}

void atomicStore(/* unknown type */ void arg_0, float arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, float arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, float arg_1) {
}

void atomicStore(/* unknown type */ void arg_0, float arg_1) {
}

void workgroupBarrier() {
}

float atomicLoad(/* unknown type */ void arg_0) {
}

float atomicLoad(/* unknown type */ void arg_0) {
}

float atomicLoad(/* unknown type */ void arg_0) {
}

float atomicLoad(/* unknown type */ void arg_0) {
}

void workgroupBarrier() {
}

float atomicAdd(/* unknown type */ void arg_0, float arg_1) {
}

float atomicAdd(/* unknown type */ void arg_0, float arg_1) {
}

float atomicAdd(/* unknown type */ void arg_0, float arg_1) {
}

float atomicAdd(/* unknown type */ void arg_0, float arg_1) {
}

void workgroupBarrier() {
}

float atomicExchange(/* unknown type */ void arg_0, float arg_1) {
}

float atomicExchange(/* unknown type */ void arg_0, float arg_1) {
}

float atomicExchange(/* unknown type */ void arg_0, float arg_1) {
}

float atomicExchange(/* unknown type */ void arg_0, float arg_1) {
}
